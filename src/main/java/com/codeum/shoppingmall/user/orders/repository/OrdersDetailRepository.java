package com.codeum.shoppingmall.user.orders.repository;

import com.codeum.shoppingmall.user.orders.domain.OrdersDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {
    Page<OrdersDetail> findAllByBuyerMemberId(Long memberId);
}
