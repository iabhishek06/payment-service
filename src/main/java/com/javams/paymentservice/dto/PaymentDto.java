package com.javams.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class PaymentDto {

    // we will get these details for payment transaction

    @JsonProperty("sender_id")
    private Long senderId;

    @JsonProperty("receiver_id")
    private Long receiverId;
    private Double amount;
    private String type;
}
