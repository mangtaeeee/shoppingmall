package com.codeum.shoppingmall.user.member.repository;

import com.codeum.shoppingmall.user.member.domain.UserLike;
import com.codeum.shoppingmall.user.member.domain.UserMember;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLIkeRepository extends JpaRepository<UserLike, Long> {
    Optional<List<UserLike>> findAllByUserMemberOrderByCreatedAtDesc(UserMember userMember);
}
