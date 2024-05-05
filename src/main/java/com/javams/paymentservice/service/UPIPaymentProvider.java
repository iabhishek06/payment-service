package com.javams.paymentservice.service;


import com.javams.paymentservice.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class UPIPaymentProvider implements IPaymentProvider{


    @Override
    public Boolean processPayment(Payment payment) {
        return null;
    }

    @Override
    public Boolean refundPayment(Payment payment) {
        return null;
    }
}
