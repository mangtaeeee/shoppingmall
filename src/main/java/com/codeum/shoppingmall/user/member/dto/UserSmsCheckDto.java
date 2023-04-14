package com.codeum.shoppingmall.user.member.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserSmsCheckDto {
    private String authKey;
    private String checkKey;
}
