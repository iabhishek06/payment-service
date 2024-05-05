package com.javams.paymentservice.service;


import com.javams.paymentservice.model.Payment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InternetBankingPaymentProvider implements IPaymentProvider{


    // we have to implement abstract methods using some templates
    // for talking with the external API in spring boot
    @Override
    public Boolean processPayment(Payment payment) {
        // we can implement the template
        // TODO: create dummy API
        //new RestTemplate().getForObject(url, BankResponse.class);

        // for now just returning true
        return true;
    }

    @Override
    public Boolean refundPayment(Payment payment) {
        return true;
    }
}
