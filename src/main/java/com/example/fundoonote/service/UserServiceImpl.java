package com.example.fundoonote.service;

import com.example.fundoonote.dto.LoginRequestDto;
import com.example.fundoonote.dto.LoginResponseDto;
import com.example.fundoonote.dto.UserRegisterRequestDto;
import com.example.fundoonote.dto.UserResponseDto;
import com.example.fundoonote.entity.User;
import com.example.fundoonote.exception.UserAlreadyExistsException;
import com.example.fundoonote.repository.UserRepository;
import com.example.fundoonote.service.UserService;
import lombok.RequiredArgsConstructor;
import com.example.fundoonote.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Override
    public UserResponseDto register(UserRegisterRequestDto dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());

        // 🔥 IMPORTANT: encode password
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getFirstName(),
                savedUser.getEmail()
        );
    }
    @Override
    public LoginResponseDto login(LoginRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDto(token);
    }
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("message",
                ex.getBindingResult().getFieldError().getDefaultMessage());
        response.put("status", 400);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}