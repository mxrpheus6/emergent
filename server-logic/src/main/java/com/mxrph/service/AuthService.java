package com.mxrph.service;

import com.mxrph.api.model.LoginBody;
import com.mxrph.api.model.RegistrationBody;
import com.mxrph.dao.UserDAO;
import com.mxrph.entity.User;
import com.mxrph.entity.enums.UserState;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserDAO userDAO,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager
    ) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signUp(RegistrationBody body) {
        User user = User.builder()
                .username(body.getUsername())
                .email(body.getEmail())
                .password(passwordEncoder.encode(body.getPassword()))
                .phoneNumber(body.getPhoneNumber())
                .userState(UserState.BASIC_STATE)
                .build();

        return userDAO.save(user);
    }

    public User signIn(LoginBody body) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.getUsername(),
                        body.getPassword()
                )
        );

        return userDAO.findByUsername(body.getUsername()).orElseThrow();
    }
}
