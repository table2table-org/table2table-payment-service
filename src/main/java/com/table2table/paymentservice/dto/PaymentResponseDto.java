package com.table2table.paymentservice.dto;

import com.table2table.paymentservice.dto.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponseDto {

    private Long paymentId;

    private Long foodRequestId; // Assuming FoodRequest entity exists

    private BigDecimal amount;

    private PaymentStatus status; // SUCCESS / FAILED / REFUNDED

    private String transactionId;

    private LocalDateTime createdAt;

}
