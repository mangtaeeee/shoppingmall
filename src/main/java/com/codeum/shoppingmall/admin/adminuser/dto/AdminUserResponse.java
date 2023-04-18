package com.codeum.shoppingmall.admin.adminuser.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminUserResponse {

    private Long id;
    private String adminEmail;

    @Builder
    public AdminUserResponse(Long id, String adminEmail) {
        this.id = id;
        this.adminEmail = adminEmail;
    }
}
