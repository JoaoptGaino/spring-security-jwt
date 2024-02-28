package com.br.joaoptgaino.jwtauthlearning.controller;


import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.request.UserParamsDTO;
import com.br.joaoptgaino.jwtauthlearning.domain.dto.user.response.UserDTO;
import com.br.joaoptgaino.jwtauthlearning.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) UserParamsDTO paramsDTO) {
        return ResponseEntity.ok(userService.findAll(pageable, paramsDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> findOne(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(Principal principal) {
        return ResponseEntity.ok(userService.findByUsername(principal.getName()));
    }
}
