package com.example.aws_sqs.repository;

import com.example.aws_sqs.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
