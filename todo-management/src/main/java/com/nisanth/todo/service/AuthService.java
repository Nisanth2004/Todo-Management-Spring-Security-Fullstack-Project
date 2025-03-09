package com.nisanth.todo.service;

import com.nisanth.todo.dto.JwtAuthResponse;
import com.nisanth.todo.dto.LoginDto;
import com.nisanth.todo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
