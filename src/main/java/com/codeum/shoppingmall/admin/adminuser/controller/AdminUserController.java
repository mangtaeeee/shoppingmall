package com.codeum.shoppingmall.admin.adminuser.controller;


import com.codeum.shoppingmall.admin.adminuser.dto.AdminUserCreate;
import com.codeum.shoppingmall.admin.adminuser.dto.AdminUserDTO;
import com.codeum.shoppingmall.admin.adminuser.dto.LoginAdminUserDTO;
import com.codeum.shoppingmall.admin.adminuser.service.AdminUserEmailService;
import com.codeum.shoppingmall.admin.adminuser.service.AdminUserService;
import com.codeum.shoppingmall.admin.product.dto.ProductAdminListDTO;
import com.codeum.shoppingmall.admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    private final AdminUserEmailService emailService;

    private final ProductService productService;

    @PostMapping("/email")
    public ResponseEntity emain(@RequestBody @Valid AdminUserDTO adminUserDTO) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(emailService.sendEmail(adminUserDTO));
    }

    @GetMapping("/email/{authString}")
    public ResponseEntity emailAuthFind(@PathVariable String authString) {
        return new ResponseEntity(emailService.authNumFind(authString).getStatusCode());
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid AdminUserCreate adminUserCreate) {
        adminUserService.join(adminUserCreate);
        return  ResponseEntity.ok(adminUserCreate.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginAdminUserDTO loginDTO) {
        return ResponseEntity.ok(adminUserService.signin(loginDTO));
    }

    @GetMapping("/productmanage")
    public Page<ProductAdminListDTO> productManagePage(@PageableDefault(page = 0, size=10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductAdminListDTO> list = productService.adminFindProduct(pageable);
        return list;
    }
}
