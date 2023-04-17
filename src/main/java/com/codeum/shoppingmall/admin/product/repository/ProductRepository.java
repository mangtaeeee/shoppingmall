package com.codeum.shoppingmall.admin.product.repository;

import com.codeum.shoppingmall.admin.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
