package com.unifor.MedMaisFacil.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        log.info("[JWT] {} {} | header = {}", request.getMethod(), request.getRequestURI(), authHeader);

        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            log.info("[JWT] sem Bearer, seguindo como anônimo");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        boolean valido = jwtService.isValidToken(token);
        log.info("[JWT] token válido? {}", valido);

        if (valido) {
            Long pacienteId = jwtService.extrairPacienteId(token);
            log.info("[JWT] pacienteId = {}", pacienteId);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(pacienteId, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("[JWT] contexto populado com pacienteId = {}", pacienteId);
        }

        filterChain.doFilter(request, response);
    }
}