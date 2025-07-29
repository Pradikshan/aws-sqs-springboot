package com.example.aws_sqs.service.impl;

import com.example.aws_sqs.dtos.request.ProductProcessRequestDto;
import com.example.aws_sqs.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SqsOrderListener {
    private final ProductService productService;

    @SqsListener("${aws.sqs.product-queue-name}")
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);

        try {
            ObjectMapper mapper = new ObjectMapper();
            ProductProcessRequestDto dto = mapper.readValue(message, ProductProcessRequestDto.class);
            productService.handleProcessOrderCount(dto);
        } catch (Exception e) {
            log.error("Failed to process message: ", e);
        }
    }

}
