package com.codeum.shoppingmall.user.member.service;

import com.codeum.shoppingmall.main.exception.AppException;

import com.codeum.shoppingmall.user.member.domain.UserMember;
import com.codeum.shoppingmall.user.member.dto.UserSignInDto;
import com.codeum.shoppingmall.user.member.dto.UserSignUpDto;
import com.codeum.shoppingmall.user.member.dto.UserSmsCheckDto;
import com.codeum.shoppingmall.user.member.dto.UserSmsDto;
import com.codeum.shoppingmall.user.member.repository.UserMemberRepository;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.codeum.shoppingmall.main.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserMemberService {

    private final UserMemberRepository userMemberRepository;

    @Value("${sms.api-key}")
    private String apiKey;

    @Value("${sms.api-secret-key}")
    private String apiSecretKey;

    @Value("${sms.from}")
    private String from;

    public String authNumberGen() {
        Random random = new Random();
        String result = "";

        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(random.nextInt(10));
            result += ran;
        }

        return result;
    }

    public UserMember findById(Long id) {
        UserMember member = userMemberRepository.findById(id)
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));
        return member;
    }

    public UserMember signUp(UserSignUpDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        String name = dto.getName();
        String rawAddress = dto.getAddress();
        String phone = dto.getPhone();
        Boolean auth = dto.getAuth();

        String postCode = rawAddress.split(",")[0];
        String address = rawAddress.split(",")[1];

        if (auth == false) {
            throw new AppException(NEED_EMAIL_AUTH);
        }

        UserMember member = UserMember.builder()
                .memberEmail(email)
                .memberPassword(password)
                .memberName(name)
                .memberPostCode(postCode)
                .memberAddress(address)
                .memberPhone(phone)
                .memberAuth(true)
                .build();

        return userMemberRepository.save(member);
    }

    public Long signIn(UserSignInDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        UserMember member = userMemberRepository.findByUserMemberEmail(email)
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));

        if (!password.equals(member.getUserMemberPassword())) {
            throw new AppException(INVALID_PASSWORD);
        }

        return member.getId();
    }

    public String smsAuth(UserSmsDto dto) {
        String authNum = authNumberGen();

        DefaultMessageService messageService = NurigoApp.INSTANCE
                .initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");

        Message message = new Message();

        message.setFrom("01076052035");
        message.setTo(dto.getTo());
        message.setText("인증 번호는 " + authNum + " 입니다.");

        try {
            messageService.send(message);
            return authNum;
        } catch (NurigoMessageNotReceivedException e) {
            System.out.println(e.getFailedMessageList());
            System.out.println(e.getMessage());
            throw new AppException(SMS_SEND_FAILED);
        } catch (NurigoEmptyResponseException e) {
            throw new AppException(SMS_NO_ANSWER);
        } catch (NurigoUnknownException e) {
            throw new AppException(INTERNAL_SERVER_ERROR);
        }
    }

    public boolean checkAuthKey(UserSmsCheckDto dto) {
        if (dto.getAuthKey().equals(dto.getCheckKey())) {
            return true;
        } else {
            throw new AppException(INVALID_AUTH_CODE);
        }
    }

    public Boolean checkDuplicateUser(String email) {
        userMemberRepository.findByUserMemberEmail(email).ifPresent(e -> {
            throw new AppException(DUPLICATED_USER);
        });
        return true;
    }
}
