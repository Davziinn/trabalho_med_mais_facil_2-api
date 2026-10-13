package com.unifor.MedMaisFacil.service.security;

import com.unifor.MedMaisFacil.exceptions.CredenciaisInvalidasException;
import com.unifor.MedMaisFacil.model.Login;
import com.unifor.MedMaisFacil.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public Login realizarLogin(Login login) {
        try {
            Authentication autenticacao = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha()));

            Paciente paciente = (Paciente) autenticacao.getPrincipal();

            if (Objects.isNull(paciente)) {
                return null;
            }

            String tokenGerado = jwtService.gerarToken(paciente.getId(), paciente.getNome());

            return Login.builder()
                    .token(tokenGerado)
                    .build();

        } catch (BadCredentialsException e) {
            throw new CredenciaisInvalidasException("Email ou Senha inválidos");
        }
    }
}
