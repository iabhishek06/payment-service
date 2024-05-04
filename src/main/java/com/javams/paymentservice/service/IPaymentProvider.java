package com.javams.paymentservice.service;

public interface IPaymentProvider {


    Boolean processPayment(Payment payment);

    Boolean refundPayment(Payment payment);
}
