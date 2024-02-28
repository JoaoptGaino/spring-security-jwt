package com.br.joaoptgaino.jwtauthlearning.controller;


import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.AuthFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.RegisterFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.SignUpFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.response.LoginResponseDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.response.UserCreatedDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.response.UserDTO;
import com.br.joaoptgaino.jwtauthlearning.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthFormDTO data) {
        LoginResponseDTO loginResponse = userService.login(data);
        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterFormDTO data) {
        UserCreatedDTO user = userService.register(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody @Valid SignUpFormDTO data) {
        UserCreatedDTO user = userService.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
