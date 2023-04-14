package com.codeum.shoppingmall.admin.product.repository;

import com.codeum.shoppingmall.admin.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
