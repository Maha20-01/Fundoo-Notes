package com.example.fundoonote.controller;


import com.example.fundoonote.dto.LoginRequestDto;
import com.example.fundoonote.dto.LoginResponseDto;
import com.example.fundoonote.dto.UserRegisterRequestDto;
import com.example.fundoonote.dto.UserResponseDto;
import com.example.fundoonote.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody UserRegisterRequestDto dto) {

        return ResponseEntity.ok(userService.register(dto));
    }
}