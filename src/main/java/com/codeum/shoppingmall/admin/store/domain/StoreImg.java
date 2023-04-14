package com.codeum.shoppingmall.admin.store.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STORE_IMG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StoreImg {

    @Id
    @Column(name = "STORE_IMG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeImgId;

    @Column(nullable = false)
    private String storeImgOriginalName;

    @Column(nullable = false)
    private String storeImgSavedName;

    @Column(nullable = false)
    private String storeImgFilePath;

    @Column(nullable = false)
    private String storeImgThumbnail;

    @Builder
    public StoreImg(String storeImgOriginalName, String storeImgSavedName, String storeImgFilePath, String storeImgThumbnail) {
        this.storeImgOriginalName = storeImgOriginalName;
        this.storeImgSavedName = storeImgSavedName;
        this.storeImgFilePath = storeImgFilePath;
        this.storeImgThumbnail = storeImgThumbnail;
    }
}
