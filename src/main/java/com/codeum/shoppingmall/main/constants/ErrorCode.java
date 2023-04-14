package com.codeum.shoppingmall.main.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터 값을 확인해주세요."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, "인증 번호가 일치하지 않습니다."),
    NEED_EMAIL_AUTH(HttpStatus.BAD_REQUEST, "이메일 인증이 필요합니다."),
    NEED_FILE_LOGO_AUTH(HttpStatus.BAD_REQUEST, "로고 사진이 필요합니다."),
    NEED_IMAGE_FILE(HttpStatus.BAD_REQUEST, "이미지 파일만 저장이 가능합니다."),

    DUPLICATED_USER(HttpStatus.CONFLICT, "중복된 유저입니다."),
    DUPLICATED_LIKE_LIST(HttpStatus.CONFLICT, "이미 관심 상품으로 등록된 상품입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다."),
    LIKE_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "관심 상품이 존재하지 않습니다."),


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.");



    private final HttpStatus httpStatus;
    private final String message;
}
