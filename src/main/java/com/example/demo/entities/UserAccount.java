package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    UUID id;
    @Column(name="username", nullable=false, length = 100, unique=true)
    String username;
    @Column(name="password", nullable=false, length = 100)
    String password;
    @Column(name="role", nullable = false,length = 20)
    private String role = "USER";
    @OneToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name="employee_id", nullable=false, unique=true)
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
