package com.codeum.shoppingmall.user.member.controller;

import com.codeum.shoppingmall.user.member.domain.UserLike;
import com.codeum.shoppingmall.user.member.dto.UserLikeDto;
import com.codeum.shoppingmall.user.member.service.UserLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class UserLikeController {

    private final UserLikeService userLikeService;

    @PostMapping("/add/{memberId}/{productId}")
    public ResponseEntity<Long> addInterestProduct(@PathVariable("memberId") Long memberId, @PathVariable("productId") Long productId) {
        UserLike userLike = userLikeService.addInterestProduct(memberId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(userLike.getId());
    }
}
