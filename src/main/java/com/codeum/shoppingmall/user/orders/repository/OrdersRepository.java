package com.codeum.shoppingmall.user.orders.repository;

import com.codeum.shoppingmall.user.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByMerchantId(String merchantId);
}
