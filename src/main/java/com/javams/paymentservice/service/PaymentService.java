package com.javams.paymentservice.service;


import com.javams.paymentservice.dto.PaymentDto;
import com.javams.paymentservice.entity.Payment;
import com.javams.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {


    public Payment createInitialPayment(PaymentDto paymentDto){
        //validations

        return Payment.builder()
                .amount(paymentDto.getAmount())
                .type(paymentDto.getType())
                .senderAccountId(paymentDto.getSenderId())
                .receiverAccountId(paymentDto.getReceiverId())
                .status("PENDING")
                .build();
    }

}
