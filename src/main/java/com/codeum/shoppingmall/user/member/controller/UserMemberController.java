package com.codeum.shoppingmall.user.member.controller;

import com.codeum.shoppingmall.user.member.domain.UserMember;
import com.codeum.shoppingmall.user.member.dto.UserSignInDto;
import com.codeum.shoppingmall.user.member.dto.UserSignUpDto;
import com.codeum.shoppingmall.user.member.dto.UserSmsCheckDto;
import com.codeum.shoppingmall.user.member.dto.UserSmsDto;
import com.codeum.shoppingmall.user.member.service.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserMemberController {

    private final UserMemberService userMemberService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@Valid @RequestBody UserSignUpDto dto) {
        UserMember newMember = userMemberService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMember.getId());
    }

    @PostMapping("/signin")
    public ResponseEntity<Long> signIn(@Valid @RequestBody UserSignInDto dto) {
        Long memberId = userMemberService.signIn(dto);
        return ResponseEntity.status(HttpStatus.OK).body(memberId);
    }

    @PostMapping("/send-auth")
    public ResponseEntity<String> sendAuthSms(@RequestBody UserSmsDto dto) {
        String authNum = userMemberService.smsAuth(dto);
        return ResponseEntity.status(HttpStatus.OK).body(authNum);
    }

    @PostMapping("/check-auth")
    public ResponseEntity<Boolean> checkAuthKey(@RequestBody UserSmsCheckDto dto) {
        boolean result = userMemberService.checkAuthKey(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/check-user")
    public ResponseEntity<Boolean> checkDuplicateUser(String email) {
        userMemberService.checkDuplicateUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/create_order/{id}")
    public ResponseEntity<UserMember> getMember(@PathVariable("id") Long id) {
        System.out.println("getmemberid : " + id);
        UserMember userMember = userMemberService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(userMember);
    }
}
