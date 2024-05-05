package com.javams.paymentservice.manager;


import com.javams.paymentservice.dto.PaymentDto;
import com.javams.paymentservice.model.Payment;
import com.javams.paymentservice.repository.AccountRepository;
import com.javams.paymentservice.repository.PaymentRepository;
import com.javams.paymentservice.service.IPaymentProvider;
import com.javams.paymentservice.service.PaymentFactory;
import com.javams.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class PaymentManager {

    // manager is for any business logic we want to write
    // I want this payment manager to be injected as dependency so using @component
    private static final int MAX_RETRIES = 3;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PaymentFactory paymentFactory;
    @Autowired
    PaymentService paymentService;

    // this transaction provide us a template for transaction
    // this transactional template we arw creating will handel all the transaction programmatically
    // this transactionManager is coming from JPA
    @Autowired
    private PlatformTransactionManager transactionManager;

    public Payment processPayment(PaymentDto paymentDto){
        // TODO: validate the request

        // first it should get the payment provider
        IPaymentProvider paymentProvider = paymentFactory.getPlan(paymentDto.getType());
        Payment payment = paymentService.createInitialPayment(paymentDto);

        // since the payment status was INIT, so saving it
        paymentRepository.save(payment);

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        //we can add some isolation level
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                // first step is to save a payment in pending state
                // in DB atleast we have to add a payment in pending state

                // changing the state of payment created from INIT to PENDING
                payment.setStatus("PENDING");

                paymentRepository.save(payment);

                // initially retries will be 0
                int retries = 0;

                // next to confirm that this transaction is completed
                boolean confirmed = false;


                // second thing is to call thw API
                // Now will check till retries is less than 3 and confirmation state is not true or we have
                // not received successful confirmation from the API

                // retry the provider part
                while(retries < MAX_RETRIES && !confirmed){
                    try{
                        // now will try to get over with whatever is done with the paymentProvider
                        Boolean paymentResponse = paymentProvider.processPayment(payment);

                        if(paymentResponse != null && paymentResponse) {
                            confirmed = true;
                        } else{
                            retries++;

                            // whatever thread is doing this task will be paused for 1 sec
                            Thread.sleep(1000);
                        }
                    } catch (Exception e){
                        // even if it gives exception, reties will be increased
                        retries++;
                    }
                }

                if(confirmed){
                    try{
                       payment.setStatus("COMPLETED");
                       paymentRepository.save(payment);

                       // always remove amount first then add
                       accountRepository.removeBalance(paymentDto.getSenderId(), paymentDto.getAmount());
                       accountRepository.addBalance(paymentDto.getReceiverId(), paymentDto.getAmount());
                       return true;

                    }
                    // now suppose above task failed but api got successful means if amount goes below 0
                    // then it will give some exception
                    // So in that case we need to do refund and after that we will roll-back
                    catch (Exception e){
                        // what if in refund we get some issue, so in that case we can also add retries there
                        // TODO : in refund we also need to add retries
                        Boolean paymentResponse = paymentProvider.refundPayment(payment);
                        status.setRollbackOnly();

                        // line no 104,105,106 is the condition where issue happens that money
                        // deducted from account but is not received by receiver

                        // so, even after retries, refund getting failed, then
                        // in that case need to raise queries on payment platform app(phonepay,gpay,...)

                        // TODO : in this case we need to give details to the support guys
                    }
                }
                // now if the API got failed, i.e. api was not confirmed
                else{
                    // we want this as atomic
                    status.setRollbackOnly();
                    // so after this rollback, payment status will be changed to INIT from PENDING
                }

                return false;
            }
        });
        return payment;
    }
}
