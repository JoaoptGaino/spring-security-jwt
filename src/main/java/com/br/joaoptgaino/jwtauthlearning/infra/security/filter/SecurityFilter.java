package com.br.joaoptgaino.jwtauthlearning.infra.security.filter;

import com.br.joaoptgaino.jwtauthlearning.infra.exceptions.UserNotFoundException;
import com.br.joaoptgaino.jwtauthlearning.infra.security.service.impl.TokenServiceImpl;
import com.br.joaoptgaino.jwtauthlearning.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static io.micrometer.common.util.StringUtils.isNotBlank;
import static org.springframework.data.util.NullableUtils.isNonNull;

@Component
@AllArgsConstructor
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenServiceImpl tokenServiceImpl;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (isNotBlank(token)) {
            String username = tokenServiceImpl.validateToken(token);
            UserDetails user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(username));
            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
