package com.codeum.shoppingmall.admin.adminuser.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ADMIN_USER")
public class AdminUser {

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false ,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean auth;

    @Builder
    public AdminUser( String password, String email, boolean auth) {
        this.password = password;
        this.email = email;
        this.auth = auth;
    }
}
