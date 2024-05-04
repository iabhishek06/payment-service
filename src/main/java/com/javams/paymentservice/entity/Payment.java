package com.javams.paymentservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENT_DB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {


    @Id
    @GeneratedValue
    private int paymentId;
    private String paymentStatus;
    private String transactionId;
    private int orderID;
    private double amount;
}
