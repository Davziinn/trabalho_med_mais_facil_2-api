package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.login.LoginRequestDTO;
import com.unifor.MedMaisFacil.dto.login.LoginResponseDTO;
import com.unifor.MedMaisFacil.mapper.LoginMapper;
import com.unifor.MedMaisFacil.model.Login;
import com.unifor.MedMaisFacil.service.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController{

    private final AuthService authService;
    private final LoginMapper loginMapper;

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> realizarLogin(@RequestBody LoginRequestDTO dto) {
        Login logado = authService.realizarLogin(loginMapper.toModel(dto));
        return ResponseEntity.ok(loginMapper.toDTO(logado));
    }
}
