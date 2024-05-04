package com.javams.paymentservice.manager;


import com.javams.paymentservice.dto.PaymentDto;
import com.javams.paymentservice.entity.Payment;
import com.javams.paymentservice.repository.AccountRepository;
import com.javams.paymentservice.repository.PaymentRepository;
import com.javams.paymentservice.service.IPaymentProvider;
import com.javams.paymentservice.service.PaymentFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentManager {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PaymentFactory paymentFactory;
    public Payment processPayment(PaymentDto paymentDto){
        // TODO : validate the request

        IPaymentProvider paymentProvider = paymentFactory.getPlan(paymentDto.getType());

    }
}
