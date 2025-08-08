package com.table2table.paymentservice.controller;

import com.table2table.paymentservice.dto.PaymentRequestDto;
import com.table2table.paymentservice.dto.PaymentResponseDto;
import com.table2table.paymentservice.service.MockPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final MockPaymentService mockPaymentService;

    @PostMapping("/pay")
    public PaymentResponseDto processPayment(@RequestBody PaymentRequestDto paymentRequest){

        return mockPaymentService.processPayment(paymentRequest);
    }

    @PostMapping("/refund")
    public PaymentResponseDto initiateRefund(@RequestBody PaymentRequestDto paymentRequest){

        return mockPaymentService.initiateRefund(paymentRequest);
    }

}
