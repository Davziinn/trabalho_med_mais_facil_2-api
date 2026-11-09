package com.unifor.MedMaisFacil.service.security;

import com.unifor.MedMaisFacil.service.TotemDispositivoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TotemKeyFilter extends OncePerRequestFilter {

    private static final String HEADER_CHAVE = "X-Totem-Key";

    private final TotemDispositivoService totemDispositivoService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        boolean totem = path.startsWith("/v1/totem/")
                || path.matches("^/v1/chamado/me/paciente/\\d+/chamado/\\d+/cancelar$");
        return !totem;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String chave = request.getHeader(HEADER_CHAVE);

        if (chave == null || chave.isBlank()) {
            log.warn("Requisição de totem sem {}: {}", HEADER_CHAVE, request.getServletPath());
        } else {
            totemDispositivoService.buscarPorChave(chave.trim()).ifPresentOrElse(totem -> {
                TotemPrincipal totemPrincipal = new TotemPrincipal(totem.getId(), totem.getUnidadeSaude().getId());

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        totemPrincipal, null, List.of(new SimpleGrantedAuthority("ROLE_TOTEM")));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }, () -> log.warn("Chave de totem não encontrada para {}", request.getServletPath()));
        }

        filterChain.doFilter(request, response);
    }
}