package com.codeum.shoppingmall.admin.store.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ADMIN_STORE")
public class AdminStore {

    @Id
    @Column(name = "admin_store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String adminStoreName;

    @Column(nullable = false)
    private String adminStoreAddress;

    @Column(nullable = false)
    private String adminStorePhone;

    @Lob
    @Column(nullable = false)
    private String adminStoreContent;

    @OneToOne
    @JoinColumn(name="STORE_IMG_ID")
    private StoreImg storeImg;

    @Builder
    public AdminStore(String adminStoreName, String adminStoreAddress, String adminStorePhone, String adminStoreContent, StoreImg storeImg) {
        this.adminStoreName = adminStoreName;
        this.adminStoreAddress = adminStoreAddress;
        this.adminStorePhone = adminStorePhone;
        this.adminStoreContent = adminStoreContent;
        this.storeImg = storeImg;
    }
}
