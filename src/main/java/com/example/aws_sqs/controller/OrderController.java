package com.example.aws_sqs.controller;

import com.example.aws_sqs.dtos.request.OrderCreateRequestDto;
import com.example.aws_sqs.dtos.response.APIResponse;
import com.example.aws_sqs.dtos.response.OrderResponseDto;
import com.example.aws_sqs.exception.RESTException;
import com.example.aws_sqs.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<APIResponse> createOrder(
            @Valid @RequestBody OrderCreateRequestDto requestDto
            ) throws RESTException {

        OrderResponseDto responseDto = orderService.handleCreateOrder(requestDto);

        APIResponse response = APIResponse
                .builder()
                .message("Order created successfully")
                .statusCode(HttpStatus.CREATED.value())
                .data(responseDto)
                .build();

        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
}
