package com.codeum.shoppingmall.user.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UserSmsDto {

    @NotBlank(message = "수신 번호를 입력하세요.")
    @Pattern(regexp = "^[0-9]+$")
    private String to;
}
