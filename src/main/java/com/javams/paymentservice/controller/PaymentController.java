package com.javams.paymentservice.controller;


import com.javams.paymentservice.dto.PaymentDto;
import com.javams.paymentservice.model.Payment;
import com.javams.paymentservice.manager.PaymentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {


    @Autowired
    PaymentManager paymentManager;


    @RequestMapping(method = RequestMethod.POST, path = "/transfer")
    public ResponseEntity<Payment> transferAmount(@RequestBody PaymentDto paymentDto){
        return ResponseEntity.ok().body(paymentManager.processPayment(paymentDto));
    }


}
