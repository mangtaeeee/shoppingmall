package com.codeum.shoppingmall.admin.adminuser.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserCreate {

    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    private boolean auth;
}




