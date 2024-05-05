package com.javams.paymentservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// this class is called as Strategy pattern
@Component
public class PaymentFactory {

    @Autowired
    InternetBankingPaymentProvider internetBankingPaymentProvider;

    @Autowired
    UPIPaymentProvider upiPaymentProvider;

    public IPaymentProvider getPlan(String paymentType){
        if(paymentType == null){
            return null;
        }

        if(paymentType.equalsIgnoreCase("INTERNET_BANKING")){
            return internetBankingPaymentProvider;
        }

        return upiPaymentProvider;
    }
}
