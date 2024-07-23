package com.mxrph.service;

import com.mxrph.api.model.request.UserUpdateRequest;
import com.mxrph.model.entity.User;
import com.mxrph.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        //TODO: add custom exception
        throw new RuntimeException("User not found");
    }

    public User updateUser(Long id, UserUpdateRequest updateRequest) {
        //TODO: add custom exception
        User user = userRepository.findById(id).orElseThrow();

        if (updateRequest.getEmail() != null) {
            user.setEmail(updateRequest.getEmail());
        }
        if (updateRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(updateRequest.getPhoneNumber());
        }
        if (updateRequest.getFirstName() != null) {
            user.setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName() != null) {
            user.setLastName(updateRequest.getLastName());
        }
        if (updateRequest.getDateOfBirth() != null) {
            user.setDateOfBirth(updateRequest.getDateOfBirth());
        }

        return userRepository.save(user);
    }
}
