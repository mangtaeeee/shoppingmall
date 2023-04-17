package com.codeum.shoppingmall.main.exception;

import com.codeum.shoppingmall.main.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.codeum.shoppingmall.main.constants.ErrorCode.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( AppException.class )
    protected ResponseEntity<?> handleCustomException(AppException ex) {
        return new ResponseEntity(
                new ErrorDto(ex.getErrorCode().getHttpStatus(), ex.getErrorCode().getMessage()),
                ex.getErrorCode().getHttpStatus()
        );
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<?> handleServerException(Exception ex) {
        System.out.println("ex = " + ex.getMessage());
        return new ResponseEntity(
                new ErrorDto(INTERNAL_SERVER_ERROR.getHttpStatus(), INTERNAL_SERVER_ERROR.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler ({ MethodArgumentNotValidException.class })
    protected ResponseEntity<ErrorDto> validException(MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}

