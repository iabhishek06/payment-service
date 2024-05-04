package com.javams.paymentservice.service;


import com.javams.paymentservice.entity.Payment;
import com.javams.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {



    @Autowired
    private PaymentRepository repository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }
    public String paymentProcessing(){

        // api should be third party payment gateway // paytm/paypal/etc
        return new Random().nextBoolean() ? "success" : "false";
    }

}
