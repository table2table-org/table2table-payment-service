package com.table2table.paymentservice.service;


import com.table2table.paymentservice.dto.PaymentRequestDto;
import com.table2table.paymentservice.dto.PaymentResponseDto;

public interface MockPaymentService {
    PaymentResponseDto processPayment(PaymentRequestDto paymentRequest);
    PaymentResponseDto initiateRefund(PaymentRequestDto paymentRequest);
}
