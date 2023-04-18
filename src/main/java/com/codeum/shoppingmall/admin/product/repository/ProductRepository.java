package com.codeum.shoppingmall.admin.product.repository;

import com.codeum.shoppingmall.admin.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productDelYn = 0")
    Page<Product> findAllProducts(Pageable pageable);

    @Query(value = "SELECT p FROM Product p " +
            "JOIN p.productHashtagList ph " +
            "JOIN p.productImgList pi " +
            "WHERE ph.productHashtagName LIKE :keyword " +
            "OR p.productName LIKE :keyword")
    Page<Product> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
