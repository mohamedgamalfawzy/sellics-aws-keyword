package com.sellics.aws.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private String exception;
    private String message;
    private HttpStatus status;
}
