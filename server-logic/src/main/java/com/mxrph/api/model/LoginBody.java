package com.mxrph.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBody {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
