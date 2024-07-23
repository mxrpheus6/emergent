package com.mxrph.service;

import com.mxrph.api.model.request.LoginBody;
import com.mxrph.api.model.request.RegistrationBody;
import com.mxrph.repository.UserRepository;
import com.mxrph.model.entity.User;
import com.mxrph.model.entity.enums.UserState;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
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

        return userRepository.save(user);
    }

    public User signIn(LoginBody body) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.getUsername(),
                        body.getPassword()
                )
        );

        return userRepository.findByUsername(body.getUsername()).orElseThrow();
    }
}
