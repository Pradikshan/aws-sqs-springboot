package com.example.aws_sqs.service;

import com.example.aws_sqs.dtos.request.OrderCreateRequestDto;
import com.example.aws_sqs.dtos.response.OrderResponseDto;
import com.example.aws_sqs.exception.RESTException;

public interface OrderService {
    OrderResponseDto handleCreateOrder(OrderCreateRequestDto requestDto) throws RESTException;
}
