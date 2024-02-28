package com.br.joaoptgaino.jwtauthlearning.service.impl;

import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.request.UserParamsDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.response.UserDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.entities.UserEntity;
import com.br.joaoptgaino.jwtauthlearning.repositories.UserRepository;
import com.br.joaoptgaino.jwtauthlearning.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserDTO(user);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable, UserParamsDTO paramsDTO) {
        return null;
    }
}
