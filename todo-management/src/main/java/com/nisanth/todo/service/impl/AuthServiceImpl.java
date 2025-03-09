package com.nisanth.todo.service.impl;

import com.nisanth.todo.dto.JwtAuthResponse;
import com.nisanth.todo.dto.LoginDto;
import com.nisanth.todo.dto.RegisterDto;
import com.nisanth.todo.entity.Role;
import com.nisanth.todo.entity.User;
import com.nisanth.todo.exception.TodoApiException;
import com.nisanth.todo.repository.RoleRepository;
import com.nisanth.todo.repository.UserRepository;
import com.nisanth.todo.security.JwtTokenProvider;
import com.nisanth.todo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String register(RegisterDto registerDto) {

        // if check username is alreday exists on db

        if(userRepository.existsByUsername(registerDto.getUsername()))
        {
            throw new TodoApiException(HttpStatus.BAD_REQUEST,"Username already exists");
        }

        // check email is alreday exists on db
        if(userRepository.existsByEmail(registerDto.getEmail()))
        {
            throw new TodoApiException(HttpStatus.BAD_REQUEST,"Email is alreday exists");
        }

        User user=new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        Set<Role> roles=new HashSet<>();
       Role userRole= roleRepository.findByName("ROLE_USER");
       roles.add(userRole);
       user.setRoles(roles);

       userRepository.save(user);
        return "User Registered Successfully";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {

       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtTokenProvider.generateToken(authentication);

       Optional<User> userOptional= userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());

       String role=null;
       if(userOptional.isPresent())
       {
           User loggedInUser=userOptional.get();
           Optional<Role> optionalRole=loggedInUser.getRoles().stream().findFirst();

           if(optionalRole.isPresent())
           {
               Role userRole=optionalRole.get();
               role=userRole.getName();
           }
       }

       // return role along with JWT token
       JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
       jwtAuthResponse.setAccessToken(token);
       jwtAuthResponse.setRole(role);

        return jwtAuthResponse;
    }
}
