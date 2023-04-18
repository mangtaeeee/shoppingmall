package com.codeum.shoppingmall.admin.store.repository;

import com.codeum.shoppingmall.admin.store.domain.AdminStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminStoreRepository extends JpaRepository<AdminStore, Long> {
    @Query(value = "SELECT s from AdminStore s " +
            "LEFT JOIN s.storeImg " +
            "WHERE s.adminStoreName LIKE %:keyword%")
    List<AdminStore> findByKeyword(@Param("keyword") String keyword);
}
