package com.nisanth.todo.config;

import com.nisanth.todo.security.JwtAuthenticationEntryPoint;
import com.nisanth.todo.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {


    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    /*authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");
                    authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");*/
                    authorize.requestMatchers("/api/auth/**").permitAll();

                    // handle preflight request
                    authorize.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        // jwt config
        http.exceptionHandling(exception->exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("nisanth")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }*/





}
