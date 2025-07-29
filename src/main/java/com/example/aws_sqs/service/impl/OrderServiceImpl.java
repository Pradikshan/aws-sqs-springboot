package com.example.aws_sqs.service.impl;

import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.sqs.SqsClient;
import com.example.aws_sqs.dtos.request.OrderCreateRequestDto;
import com.example.aws_sqs.dtos.response.OrderResponseDto;
import com.example.aws_sqs.exception.RESTException;
import com.example.aws_sqs.model.Order;
import com.example.aws_sqs.model.Product;
import com.example.aws_sqs.repository.OrderRepository;
import com.example.aws_sqs.repository.ProductRepository;
import com.example.aws_sqs.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final SqsClient sqsClient;

    @Value("${aws.sqs.product-queue-url}")
    private String queueUrl;

    public OrderResponseDto handleCreateOrder(OrderCreateRequestDto requestDto) throws RESTException{
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new RESTException("Product not found!", HttpStatus.NOT_FOUND.value()));

        Order newOrder = Order
                .builder()
                .product(product)
                .count(requestDto.getCount())
                .createdDate(LocalDateTime.now())
                .build();

        orderRepository.save(newOrder);

        String messageBody = String.format("{\"productId\":%d,\"count\":%d}", product.getId(), requestDto.getCount());

        sqsClient.sendMessage(builder -> builder.queueUrl(queueUrl).messageBody(messageBody));

        return OrderResponseDto
                .builder()
                .orderId(newOrder.getId())
                .productId(newOrder.getProduct().getId())
                .productName(newOrder.getProduct().getProductName())
                .count(newOrder.getCount())
                .createdDate(newOrder.getCreatedDate())
                .build();
    }
}
