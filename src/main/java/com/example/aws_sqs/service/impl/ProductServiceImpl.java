package com.example.aws_sqs.service.impl;

import com.example.aws_sqs.dtos.request.ProductProcessRequestDto;
import com.example.aws_sqs.exception.RESTException;
import com.example.aws_sqs.model.Product;
import com.example.aws_sqs.repository.ProductRepository;
import com.example.aws_sqs.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public void handleProcessOrderCount(ProductProcessRequestDto requestDto) throws RESTException {
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new RESTException("Product not found!", HttpStatus.NOT_FOUND.value()));

        product.setTotalCount(product.getTotalCount() - requestDto.getCount());

        productRepository.save(product);

        System.out.println("Product new count: " + product.getTotalCount());

    }
}
