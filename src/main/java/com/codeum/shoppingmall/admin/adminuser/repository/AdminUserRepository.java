package com.codeum.shoppingmall.admin.adminuser.repository;

import com.codeum.shoppingmall.admin.adminuser.domain.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

    Optional<AdminUser> findByEmail(String email);

}
