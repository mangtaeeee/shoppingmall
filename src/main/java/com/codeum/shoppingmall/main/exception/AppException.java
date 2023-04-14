package com.codeum.shoppingmall.main.exception;

import com.codeum.shoppingmall.main.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{
    private final ErrorCode errorCode;
}
