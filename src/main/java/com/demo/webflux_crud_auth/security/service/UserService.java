package com.demo.webflux_crud_auth.security.service;

import com.demo.webflux_crud_auth.exception.CustomException;
import com.demo.webflux_crud_auth.security.dto.LoginDto;
import com.demo.webflux_crud_auth.security.dto.RegisterDto;
import com.demo.webflux_crud_auth.security.dto.TokenDto;
import com.demo.webflux_crud_auth.security.entity.User;
import com.demo.webflux_crud_auth.security.enums.Role;
import com.demo.webflux_crud_auth.security.jwt.JwtProvider;
import com.demo.webflux_crud_auth.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public Mono<TokenDto> login(LoginDto loginDto) {
        return userRepository.findByUsernameOrEmail(loginDto.getUsername(), loginDto.getPassword())
                .filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
                .map(user -> new TokenDto(jwtProvider.generateToken(user)))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "bad credentials")));
    }

    public Mono<User> registerUser(RegisterDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(Role.USER.name())
                .build();

        Mono<Boolean> userExists = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).hasElement();

        return userExists.flatMap(exists -> exists
                ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "user already exists"))
                : userRepository.save(user));
    }
}
