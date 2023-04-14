package com.codeum.shoppingmall.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@ToString
public class ErrorDto {
    private HttpStatus status;
    private String message;
}
