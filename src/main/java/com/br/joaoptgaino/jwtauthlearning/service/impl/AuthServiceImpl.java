package com.br.joaoptgaino.jwtauthlearning.service.impl;

import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.AuthFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.RegisterFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.SignUpFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.response.LoginResponseDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.response.UserCreatedDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.response.UserDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.entities.UserEntity;
import com.br.joaoptgaino.jwtauthlearning.domain.enums.UserRole;
import com.br.joaoptgaino.jwtauthlearning.infra.security.service.TokenService;
import com.br.joaoptgaino.jwtauthlearning.repositories.UserRepository;
import com.br.joaoptgaino.jwtauthlearning.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserCreatedDTO register(RegisterFormDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity user = new UserEntity(data.username(), encryptedPassword, data.role());
        userRepository.save(user);
        return new UserCreatedDTO(user);
    }

    @Override
    public UserCreatedDTO signUp(SignUpFormDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity user = new UserEntity(data.username(), encryptedPassword, UserRole.USER);
        userRepository.save(user);
        return new UserCreatedDTO(user);
    }

    @Override
    public LoginResponseDTO login(AuthFormDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }
}
