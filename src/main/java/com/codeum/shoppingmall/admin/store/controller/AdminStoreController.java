package com.codeum.shoppingmall.admin.store.controller;

import com.codeum.shoppingmall.admin.store.dto.AdminStoreCreate;
import com.codeum.shoppingmall.admin.store.service.AdminStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminStoreController {

    private final AdminStoreService adminStoreService;

    @PostMapping("/store/upload")
    public void storeSave(@RequestBody AdminStoreCreate create) throws Exception {
        System.out.println("요청 들어온 데이터 : "+create.toString());
        adminStoreService.saveStore(create);
    }

}
