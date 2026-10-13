package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.login.LoginRequestDTO;
import com.unifor.MedMaisFacil.dto.login.LoginResponseDTO;
import com.unifor.MedMaisFacil.model.Login;

public interface LoginMapper {

    Login toModel (LoginRequestDTO dto);

    LoginResponseDTO toDTO (Login model);
}
