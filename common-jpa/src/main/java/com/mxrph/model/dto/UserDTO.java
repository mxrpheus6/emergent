package com.mxrph.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
