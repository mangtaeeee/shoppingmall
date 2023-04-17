package com.codeum.shoppingmall.admin.product.repository;

import com.codeum.shoppingmall.admin.product.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
}
