package com.example.aws_sqs.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RESTException extends RuntimeException {
    private final int statusCode;

    private final String timeStamp;

    public RESTException(String message, int statusCode) {
      super(message);
      this.statusCode = statusCode;
      this.timeStamp = LocalDateTime.now().toString();
    }
}
