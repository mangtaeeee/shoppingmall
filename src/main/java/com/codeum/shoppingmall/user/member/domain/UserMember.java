package com.codeum.shoppingmall.user.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "userMember", cascade = CascadeType.ALL)
    private List<UserLike> userLikes = new ArrayList<>();

    @Builder
    public UserMember(String memberEmail, String memberPassword, String memberName, String memberPostCode, String memberAddress, String memberPhone, boolean memberAuth) {
        this.userMemberEmail = memberEmail;
        this.userMemberPassword = memberPassword;
        this.userMemberName = memberName;
        this.userMemberPostCode = memberPostCode;
        this.userMemberAddress = memberAddress;
        this.userMemberPhone = memberPhone;
        this.userMemberAuth = memberAuth;
    }
}
