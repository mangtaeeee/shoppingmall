package com.codeum.shoppingmall.admin.store.service;

import com.codeum.shoppingmall.admin.store.domain.AdminStore;
import com.codeum.shoppingmall.admin.store.dto.AdminStoreCreate;
import com.codeum.shoppingmall.admin.store.repository.AdminStoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminStoreServiceTest {

    @Autowired
    private AdminStoreService adminStoreService;

    @Autowired
    private AdminStoreRepository adminStoreRepository;

    @BeforeEach
    void clean(){
        adminStoreRepository.deleteAll();
    }
    @Test
    @DisplayName("상점 등록 확인")
    void 상점등록() throws IOException {
        //given

        MockMultipartFile file = new MockMultipartFile("file", "다운로드.jpeg", "image/png", new FileInputStream("/Users/gimtaeyun/Downloads/다운로드.jpeg"));

        AdminStoreCreate create = AdminStoreCreate.builder()
                .adminStoreName("상점 이름입니다.")
                .adminStoreAddress("상점 주소입니다.")
                .adminStoreContent("상점 내용입니다.")
                .adminStorePhone("상점 폰 번호입니다.")
                .storeImgFile(file)
                .build();



        //when
        adminStoreService.saveStore(create);

        //then
        Assertions.assertEquals(1L, adminStoreRepository.count());
        AdminStore adminStore = adminStoreRepository.findAll().get(0);
        Assertions.assertEquals("상점 이름입니다.", adminStore.getAdminStoreName());

    }


}