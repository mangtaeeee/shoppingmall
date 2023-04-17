package com.codeum.shoppingmall.user.member.service;

import com.codeum.shoppingmall.main.exception.AppException;
import com.codeum.shoppingmall.user.member.domain.UserLike;
import com.codeum.shoppingmall.user.member.domain.UserMember;
import com.codeum.shoppingmall.user.member.dto.UserLikeDto;
import com.codeum.shoppingmall.user.member.repository.UserLIkeRepository;
import com.codeum.shoppingmall.user.member.repository.UserMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codeum.shoppingmall.main.constants.ErrorCode.LIKE_LIST_NOT_FOUND;
import static com.codeum.shoppingmall.main.constants.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserLikeService {

    private final UserMemberRepository userMemberRepository;
    private final UserLIkeRepository userLIkeRepository;

    public List<UserLikeDto> getInterestProduct(Long memberId) {

        UserMember userMember = userMemberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));

        List<UserLike> userLikes = userLIkeRepository.findAllByUserMemberOrderByCreatedAtDesc(userMember)
                .orElseThrow(() -> new AppException(LIKE_LIST_NOT_FOUND));

        List<UserLikeDto> userLikeDtoList = new ArrayList<>();

        for (UserLike userLike : userLikes) {
            userLikeDtoList.add(UserLikeDto.toLikeList(userLike));
        }

        return userLikeDtoList;
    }

    public Long addInterestProduct(Long memberId, Long likeId) {
        return null;
    }

    public void deleteInterestProduct(Long memberId, Long likeId) {
    }
}
