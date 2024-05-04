package com.javams.paymentservice.service;


import org.springframework.stereotype.Service;

@Service
public class InternetBankingPaymentProvider implements IPaymentProvider{


    @Override
    public Boolean processPayment(Payment payment) {
        return null;
    }

    @Override
    public Boolean refundPayment(Payment payment) {
        return null;
    }
}
