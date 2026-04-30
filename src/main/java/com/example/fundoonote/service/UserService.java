package com.example.fundoonote.service;

import com.example.fundoonote.dto.UserRegisterRequestDto;
import com.example.fundoonote.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegisterRequestDto dto);
}