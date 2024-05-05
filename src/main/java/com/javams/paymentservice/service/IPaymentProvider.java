package com.javams.paymentservice.service;

import com.javams.paymentservice.model.Payment;

public interface IPaymentProvider {


    // both method should be atomic in nature so return type boolean is used
    Boolean processPayment(Payment payment);

    Boolean refundPayment(Payment payment);

}
