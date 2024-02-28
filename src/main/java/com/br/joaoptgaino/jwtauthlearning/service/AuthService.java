package com.br.joaoptgaino.jwtauthlearning.service;

import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.AuthFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.RegisterFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.request.SignUpFormDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.response.LoginResponseDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.auth.response.UserCreatedDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.response.UserDTO;

public interface AuthService {
    UserCreatedDTO register(RegisterFormDTO data);


    UserCreatedDTO signUp(SignUpFormDTO data);


    LoginResponseDTO login(AuthFormDTO data);
}
