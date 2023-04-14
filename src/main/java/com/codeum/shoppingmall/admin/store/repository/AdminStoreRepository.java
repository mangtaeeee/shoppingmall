package com.codeum.shoppingmall.admin.store.repository;

import com.codeum.shoppingmall.admin.store.domain.AdminStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminStoreRepository extends JpaRepository<AdminStore, Long> {
}
