package com.javams.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_system")
public class Payment {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long senderAccountId;
    Long receiverAccountId;
    Double amount;
    String type;
    String status;
}
