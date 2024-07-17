package com.mxrph.service;

import com.mxrph.api.model.LoginBody;
import com.mxrph.api.model.RegistrationBody;
import com.mxrph.dao.AppUserDAO;
import com.mxrph.entity.AppUser;
import com.mxrph.entity.enums.UserState;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserDAO appUserDAO;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(AppUserDAO appUserDAO,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager
    ) {
        this.appUserDAO = appUserDAO;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AppUser signUp(RegistrationBody body) {
        AppUser user = AppUser.builder()
                .username(body.getUsername())
                .email(body.getEmail())
                .password(passwordEncoder.encode(body.getPassword()))
                .phoneNumber(body.getPhoneNumber())
                .userState(UserState.BASIC_STATE)
                .build();

        return appUserDAO.save(user);
    }

    public AppUser signIn(LoginBody body) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.getUsername(),
                        body.getPassword()
                )
        );

        return appUserDAO.findByUsername(body.getUsername()).orElseThrow();
    }
}
