package com.unifor.MedMaisFacil.service.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey secretKey;
    private final long expiracaoMs;

    public JwtServiceImpl(@Value("${jwt.secret}") String chaveSecreta,
                          @Value("${jwt.expiration-minutes}") long expiracaoMinutos) {
        this.secretKey = Keys.hmacShaKeyFor(chaveSecreta.getBytes(StandardCharsets.UTF_8));
        this.expiracaoMs = expiracaoMinutos * 60 * 1000;
    }

    @Override
    public String gerarToken(Long pacienteId, String nome) {
        Date agora = new Date();
        return Jwts.builder()
                .subject(String.valueOf(pacienteId))
                .claim("nome", nome)
                .issuedAt(agora)
                .expiration(new Date(agora.getTime() + expiracaoMs))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Long extrairPacienteId(String token) {
        String subject = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.valueOf(subject);
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
