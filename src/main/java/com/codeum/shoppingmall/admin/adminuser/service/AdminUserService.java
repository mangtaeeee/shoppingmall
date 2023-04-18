package com.codeum.shoppingmall.admin.adminuser.service;


import com.codeum.shoppingmall.admin.adminuser.domain.AdminUser;
import com.codeum.shoppingmall.admin.adminuser.dto.AdminUserCreate;
import com.codeum.shoppingmall.admin.adminuser.dto.AdminUserResponse;
import com.codeum.shoppingmall.admin.adminuser.dto.LoginAdminUserDTO;
import com.codeum.shoppingmall.admin.adminuser.repository.AdminUserRepository;
import com.codeum.shoppingmall.main.constants.ErrorCode;
import com.codeum.shoppingmall.main.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codeum.shoppingmall.main.constants.ErrorCode.INVALID_PASSWORD;

@Service
@RequiredArgsConstructor
public class AdminUserService {


    private final AdminUserRepository adminUserRepository;

    @Transactional
    public void join(AdminUserCreate adminUserCreate) {

        if (adminUserCreate.isAuth() == false) {
            throw new IllegalArgumentException("이메일 인증이 필요합니다.");
        }

        AdminUser user = AdminUser.builder()
                .auth(adminUserCreate.isAuth())
                .email(adminUserCreate.getEmail())
                .password(adminUserCreate.getPassword())
                .build();
        adminUserRepository.save(user);
    }

    public AdminUserResponse signin(LoginAdminUserDTO loginDto) {

        AdminUser admin = adminUserRepository.findByEmail(loginDto.getEmail()).orElseThrow(
                ()-> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!loginDto.getPassword().equals(admin.getPassword())) {
            throw new AppException(INVALID_PASSWORD);
        }

        return AdminUserResponse.builder()
                .adminEmail(admin.getEmail())
                .build();
    }

}
