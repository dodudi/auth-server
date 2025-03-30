package com.rudy.auth.user.domain;

import com.rudy.auth.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleInfo roleInfo;

    public UserRole(UserInfo userInfo, RoleInfo roleInfo) {
        this.userInfo = userInfo;
        userInfo.getUserRoles().add(this);

        this.roleInfo = roleInfo;
    }

    public SimpleGrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority(roleInfo.getRoleName());
    }
}
