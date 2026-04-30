package com.example.fundoonote.service;

import com.example.fundoonote.dto.LoginRequestDto;
import com.example.fundoonote.dto.LoginResponseDto;
import com.example.fundoonote.dto.UserRegisterRequestDto;
import com.example.fundoonote.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegisterRequestDto dto);
    LoginResponseDto login(LoginRequestDto dto);
}