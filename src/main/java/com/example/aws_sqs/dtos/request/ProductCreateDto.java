package com.example.aws_sqs.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    @NotBlank
    @Size(message = "Product name cannot be empty", max = 255)
    private String productName;

    private Integer count;
}
