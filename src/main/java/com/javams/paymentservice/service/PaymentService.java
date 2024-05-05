package com.javams.paymentservice.service;


import com.javams.paymentservice.dto.PaymentDto;
import com.javams.paymentservice.model.Payment;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    // first we need to create payment as pending payment with the help of builder pattern
    public Payment createInitialPayment(PaymentDto paymentDto){
        //validations

        return Payment.builder()
                .amount(paymentDto.getAmount())
                .type(paymentDto.getType())
                .senderAccountId(paymentDto.getSenderId())
                .receiverAccountId(paymentDto.getReceiverId())
                .status("INIT")
                .build();
    }

}
