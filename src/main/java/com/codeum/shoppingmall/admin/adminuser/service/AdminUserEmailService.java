package com.codeum.shoppingmall.admin.adminuser.service;

import com.codeum.shoppingmall.admin.adminuser.dto.AdminUserAuthNumDTO;
import com.codeum.shoppingmall.admin.adminuser.dto.AdminUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AdminUserEmailService {

    private final JavaMailSender emailSender;
    private String authNum; //랜덤 인증 코드

    //랜덤 인증 코드 생성
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

    //메일 양식 작성
    public MimeMessage createEmailForm(AdminUserDTO adminUserDTO) throws MessagingException, UnsupportedEncodingException {

        createCode(); //인증 코드 생성
        String setFrom = "jwk@codeum.tech"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
        String toEmail = adminUserDTO.getEmail(); //받는 사람
        String title = "회원가입 인증 번호"; //제목

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(authNum, "utf-8", "html");

        return message;
    }

    //실제 메일 전송
    public String sendEmail(AdminUserDTO adminUserDTO) throws MessagingException, UnsupportedEncodingException {

        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(adminUserDTO);
        //실제 메일 전송
        emailSender.send(emailForm);

        AdminUserAuthNumDTO authNumDTO = AdminUserAuthNumDTO.builder()
                .authNum(authNum)
                .build();

        return authNumDTO.getAuthNum(); //인증 코드 반환
    }

    public ResponseEntity authNumFind(String authString) {

        if (authNum.equals(authString)) {
            return ResponseEntity.ok(authString);
        } else {
            return ResponseEntity.badRequest().body(authString);
        }
    }
}
