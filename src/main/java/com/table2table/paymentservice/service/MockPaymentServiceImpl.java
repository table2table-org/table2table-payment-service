package com.table2table.paymentservice.service;

import com.table2table.paymentservice.dto.PaymentRequestDto;
import com.table2table.paymentservice.dto.PaymentResponseDto;
import com.table2table.paymentservice.dto.enums.PaymentStatus;
import com.table2table.paymentservice.entity.Payments;
import com.table2table.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MockPaymentServiceImpl implements MockPaymentService {

    private final PaymentRepository paymentRepository;

    public MockPaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponseDto processPayment(PaymentRequestDto paymentRequest) {
        // Logically simulate a payment success (could fail too)
        Payments payment = new Payments();
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        long randomNumber = 1_000_000_000L + (long)(Math.random() * 9_000_000_000L);
        System.out.println("Processed mock payment of ₹" + paymentRequest.getAmount() + " for user ID: " + paymentRequest.getFoodRequestId());
        payment.setFoodRequestId(paymentRequest.getFoodRequestId());
        payment.setStatus(PaymentStatus.CONFIRMED);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setTransactionId(Long.toString(randomNumber));
        payment.setAmount(paymentRequest.getAmount());
        paymentRepository.save(payment);

        paymentResponseDto.setPaymentId(payment.getPaymentId());
        paymentResponseDto.setAmount(payment.getAmount());
        paymentResponseDto.setStatus(payment.getStatus());
        paymentResponseDto.setTransactionId(payment.getTransactionId());
        paymentResponseDto.setCreatedAt(payment.getCreatedAt());
        paymentResponseDto.setFoodRequestId(payment.getFoodRequestId());
        return paymentResponseDto; // Always succeeds in mock
    }

    @Override
    public PaymentResponseDto initiateRefund(PaymentRequestDto paymentRequest) {
        Optional<Payments> payment = Optional.of(new Payments());
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();

        payment = paymentRepository.findById(paymentRequest.getPaymentId());
        payment.ifPresent(payments -> payments.setStatus(PaymentStatus.REFUNDED));
        paymentRepository.save(payment.get());

        paymentResponseDto.setPaymentId(payment.get().getPaymentId());
        paymentResponseDto.setAmount(payment.get().getAmount());
        paymentResponseDto.setStatus(payment.get().getStatus());
        paymentResponseDto.setTransactionId(payment.get().getTransactionId());
        paymentResponseDto.setCreatedAt(payment.get().getCreatedAt());
        paymentResponseDto.setFoodRequestId(payment.get().getFoodRequestId());

        System.out.println("Initiated mock refund of ₹" + paymentRequest.getAmount() + " to user ID: " + paymentRequest.getFoodRequestId());
        return paymentResponseDto;
    }
}
