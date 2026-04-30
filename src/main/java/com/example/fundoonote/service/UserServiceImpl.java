package com.example.fundoonote.service;

import com.example.fundoonote.dto.UserRegisterRequestDto;
import com.example.fundoonote.dto.UserResponseDto;
import com.example.fundoonote.entity.User;
import com.example.fundoonote.repository.UserRepository;
import com.example.fundoonote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto register(UserRegisterRequestDto dto) {

        // check duplicate email
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // map DTO → Entity
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        // save
        User savedUser = userRepository.save(user);

        // return response
        return new UserResponseDto(
                savedUser.getFirstName(),
                savedUser.getEmail()
        );
    }
}