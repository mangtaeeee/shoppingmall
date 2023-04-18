package com.codeum.shoppingmall.admin.adminuser.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserDTO {

    private String adminId;

    private String password;

    private String email;

    private boolean auth;
}
