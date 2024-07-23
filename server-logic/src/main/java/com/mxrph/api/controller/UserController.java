package com.mxrph.api.controller;

import com.mxrph.api.model.request.UserUpdateRequest;
import com.mxrph.model.dto.UserDTO;
import com.mxrph.model.entity.User;
import com.mxrph.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> authenticatedUser() {
        return ResponseEntity.ok(userService.getCurrentUser().toDTO());
    }

    @PutMapping("/me/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserUpdateRequest updateRequest) {
        User currentUser = userService.getCurrentUser();
        User updatedUser = userService.updateUser(currentUser.getId(), updateRequest);
        return ResponseEntity.ok(updatedUser.toDTO());
    }

}
