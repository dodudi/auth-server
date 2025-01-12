package com.rudy.auth.user;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime create_date;

    @LastModifiedDate
    private LocalDateTime update_date;
}
