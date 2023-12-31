package com.codeum.shoppingmall.user.member.repository;

import com.codeum.shoppingmall.user.member.domain.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMemberRepository extends JpaRepository<UserMember, Long> {
    Optional<UserMember> findByUserMemberEmail(String email);
}
