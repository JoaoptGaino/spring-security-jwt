package com.br.joaoptgaino.jwtauthlearning.service;

import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.request.UserParamsDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.response.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO findByUsername(String username);

    Page<UserDTO> findAll(Pageable pageable, UserParamsDTO paramsDTO);
}
