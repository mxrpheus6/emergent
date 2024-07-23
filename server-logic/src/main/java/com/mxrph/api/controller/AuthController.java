package com.mxrph.api.controller;

import com.mxrph.api.model.request.LoginBody;
import com.mxrph.api.model.response.LoginResponse;
import com.mxrph.api.model.request.RegistrationBody;
import com.mxrph.model.entity.User;
import com.mxrph.service.AuthService;
import com.mxrph.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-api")
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> signUp(@RequestBody RegistrationBody body) {
        User user = authService.signUp(body);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> signIn(@RequestBody LoginBody body) {
        User user = authService.signIn(body);

        String jwtToken = jwtService.generateToken(user);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getJwtExpiration())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
