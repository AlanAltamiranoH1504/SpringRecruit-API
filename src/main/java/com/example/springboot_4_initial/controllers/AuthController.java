package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.user.RequestUserDTO;
import com.example.springboot_4_initial.services.interfaces.IUserSevice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserSevice iUserSevice;

    @PostMapping("/recruiter")
    public ResponseEntity<?> createRecruiterUser(@Valid @RequestBody RequestUserDTO requestUserDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserSevice.createUser(requestUserDTO, true));
    }
}
