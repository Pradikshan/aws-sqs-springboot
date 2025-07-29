package com.example.aws_sqs.exception;

import com.example.aws_sqs.dtos.response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RESTException.class)
    public ResponseEntity<APIResponse> handleRESTException(RESTException exception) {
        APIResponse response = APIResponse
                .builder()
                .timeStamp(LocalDateTime.now().toString())
                .statusCode(exception.getStatusCode())
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
