package com.example.aws_sqs.service;

import com.example.aws_sqs.dtos.request.ProductProcessRequestDto;
import com.example.aws_sqs.exception.RESTException;

public interface ProductService {
    void handleProcessOrderCount(ProductProcessRequestDto requestDto) throws RESTException;
}
