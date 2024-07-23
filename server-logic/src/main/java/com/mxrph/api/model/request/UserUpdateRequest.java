package com.mxrph.api.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateRequest {
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
