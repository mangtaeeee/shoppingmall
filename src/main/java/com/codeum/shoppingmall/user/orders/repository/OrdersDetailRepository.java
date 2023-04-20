package com.codeum.shoppingmall.user.orders.repository;

import com.codeum.shoppingmall.admin.store.dto.AdminStoreProductImg;
import com.codeum.shoppingmall.user.orders.domain.Orders;
import com.codeum.shoppingmall.user.orders.domain.OrdersDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {
    Page<OrdersDetail> findAllByBuyerMemberId(Long memberId, Pageable productPageable);
    OrdersDetail findByOrders(Orders orders);
}
