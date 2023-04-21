package com.codeum.shoppingmall.admin.product.repository;

import com.codeum.shoppingmall.admin.product.domain.Product;
import com.codeum.shoppingmall.admin.store.dto.AdminStoreProductImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productDelYn = false")
    Page<Product> findAllProducts(Pageable pageable);

    @EntityGraph(attributePaths = {"productHashtagList", "productImgList"})
    @Query("SELECT p FROM Product p " +
            "INNER JOIN p.productHashtagList ph " +
            "INNER JOIN p.productImgList pi " +
            "WHERE ph.productHashtagName LIKE %:keyword% " +
            "OR p.productName LIKE %:keyword%")
    Page<Product> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<AdminStoreProductImg> findAllByAdminStoreId(Long id, Pageable productPageable);
}
