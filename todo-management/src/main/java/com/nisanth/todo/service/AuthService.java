package com.nisanth.todo.service;

import com.nisanth.todo.dto.LoginDto;
import com.nisanth.todo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
