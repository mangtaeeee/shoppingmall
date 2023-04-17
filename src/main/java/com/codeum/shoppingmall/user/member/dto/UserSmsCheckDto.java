package com.codeum.shoppingmall.user.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class UserSmsCheckDto {
    private String authKey;
    private String checkKey;
}
