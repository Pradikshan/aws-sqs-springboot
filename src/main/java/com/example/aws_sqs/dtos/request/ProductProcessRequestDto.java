package com.example.aws_sqs.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductProcessRequestDto {
    @NotNull
    private Long productId;

    @NotNull
    private Integer count;
}
