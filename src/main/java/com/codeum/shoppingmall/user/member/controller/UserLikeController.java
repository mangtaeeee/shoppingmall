package com.codeum.shoppingmall.user.member.controller;

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

    @GetMapping("/get/{memberId}")
    public ResponseEntity<List<UserLikeDto>> getInterestProduct(@PathVariable("memberId") Long memberId) {
        List<UserLikeDto> result = userLikeService.getInterestProduct(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/add/{memberId}/{productId}")
    public ResponseEntity<Long> addInterestProduct(@PathVariable("memberId") Long memberId, @PathVariable("productId") Long productId) {
        Long result = userLikeService.addInterestProduct(memberId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/delete/{memberId}/{likeId}")
    public ResponseEntity deleteInterestProduct(@PathVariable("memberId") Long memberId, @PathVariable("likeId") Long likeId) {
        userLikeService.deleteInterestProduct(memberId, likeId);
        return ResponseEntity.noContent().build();
    }
}
