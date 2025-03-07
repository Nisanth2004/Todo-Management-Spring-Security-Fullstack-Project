package com.nisanth.todo.controller;

import com.nisanth.todo.dto.JwtAuthResponse;
import com.nisanth.todo.dto.LoginDto;
import com.nisanth.todo.dto.RegisterDto;
import com.nisanth.todo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // build register API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto)
    {
        String response= authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // build login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto)
    {
       String token= authService.login(loginDto);

       JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
       jwtAuthResponse.setAccessToken(token);
       return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
    }
}
