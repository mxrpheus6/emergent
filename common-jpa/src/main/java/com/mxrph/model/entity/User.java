package com.mxrph.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mxrph.model.dto.UserDTO;
import com.mxrph.model.entity.enums.UserState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false, length = 25)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(length = 15)
    private String firstName;

    @Column(length = 30)
    private String lastName;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private LocalDate dateOfRegistration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserState userState;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .username(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
