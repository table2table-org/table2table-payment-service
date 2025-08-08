package com.table2table.paymentservice.repository;


import com.table2table.paymentservice.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentRepository extends JpaRepository<Payments, Long>, JpaSpecificationExecutor<Payments> {
}

