package com.mxrph.api.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponse {
    private String token;
    private Long expiresIn;
}
