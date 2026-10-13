package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.login.LoginRequestDTO;
import com.unifor.MedMaisFacil.dto.login.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {

    @PostMapping
    ResponseEntity<LoginResponseDTO> realizarLogin (@RequestBody LoginRequestDTO dto);
}
