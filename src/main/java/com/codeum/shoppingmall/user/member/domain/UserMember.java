package com.codeum.shoppingmall.user.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class UserMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_member_id")
    private Long id;

    @Column(unique = true)
    private String userMemberEmail;
    private String userMemberPassword;
    private String userMemberName;
    private String userMemberPostCode;
    private String userMemberAddress;
    private String userMemberPhone;
    private Boolean userMemberAuth;

    @Builder
    public UserMember(String email, String password, String name, String postCode, String address, String phone, Boolean auth) {
        this.userMemberEmail = email;
        this.userMemberPassword = password;
        this.userMemberName = name;
        this.userMemberPostCode = postCode;
        this.userMemberAddress = address;
        this.userMemberPhone = phone;
        this.userMemberAuth = auth;
    }
}
