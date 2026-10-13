package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.login.LoginRequestDTO;
import com.unifor.MedMaisFacil.dto.login.LoginResponseDTO;
import com.unifor.MedMaisFacil.model.Login;
import org.springframework.stereotype.Component;

@Component
public class LoginMapperImpl implements LoginMapper {

    @Override
    public Login toModel(LoginRequestDTO dto) {
        return Login.builder()
                .email(dto.email())
                .senha(dto.senha())
                .build();
    }

    @Override
    public LoginResponseDTO toDTO(Login model) {
        return new LoginResponseDTO(
                model.getToken()
        );
    }
}
