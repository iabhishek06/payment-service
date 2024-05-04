package com.javams.paymentservice.controller;


import com.javams.paymentservice.entity.Payment;
import com.javams.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {


    @Autowired
    private PaymentService service;


    @PostMapping("/do-payment")
    public Payment doPayment(@RequestBody Payment payment){

        return service.doPayment(payment);
    }


}
