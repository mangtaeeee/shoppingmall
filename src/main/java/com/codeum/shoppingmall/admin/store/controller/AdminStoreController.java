package com.codeum.shoppingmall.admin.store.controller;

import com.codeum.shoppingmall.admin.store.dto.AdminStoreCreate;
import com.codeum.shoppingmall.admin.store.dto.AdminStoreDTO;
import com.codeum.shoppingmall.admin.store.dto.AdminStoreSearchDTO;
import com.codeum.shoppingmall.admin.store.service.AdminStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminStoreController {

    private final AdminStoreService adminStoreService;

    @PostMapping("/store/upload")
    public void storeSave(@Valid AdminStoreCreate create) throws Exception {
        adminStoreService.saveStore(create);
    }

    @GetMapping("/store/findAll")
    public List<AdminStoreDTO> findAll(){
        return adminStoreService.findAll();
    }

    @GetMapping("/store/findAllUser")
    public ResponseEntity findAllUser(){
        return ResponseEntity.ok(adminStoreService.findStoreUser());
    }

    @GetMapping("/store/searchstore")
    public ResponseEntity<List<AdminStoreSearchDTO>> searchStore(@Param("keyword") String keyword){
        List<AdminStoreSearchDTO> result = adminStoreService.searchStore(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
