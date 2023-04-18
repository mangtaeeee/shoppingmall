package com.codeum.shoppingmall.user.member.repository;

import com.codeum.shoppingmall.admin.product.domain.Product;
import com.codeum.shoppingmall.user.member.domain.UserLike;
import com.codeum.shoppingmall.user.member.domain.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike, Long> {
    Optional<List<UserLike>> findAllByUserMemberOrderByUpdatedAtDesc(UserMember userMember);

    Optional<UserLike> findByUserMemberAndProduct(UserMember userMember, Product product);
}
