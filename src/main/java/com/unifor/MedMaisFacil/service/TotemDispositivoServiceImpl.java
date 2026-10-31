package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.mapper.TotemDispositivoMapper;
import com.unifor.MedMaisFacil.model.TotemDispositivo;
import com.unifor.MedMaisFacil.repository.TotemDispositivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TotemDispositivoServiceImpl implements TotemDispositivoService {

    private final TotemDispositivoRepository totemDispositivoRepository;
    private final TotemDispositivoMapper totemDispositivoMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<TotemDispositivo> buscarPorChave(String chaveBruta) {
        if (chaveBruta == null || chaveBruta.isBlank()) {
            return Optional.empty();
        }

        return totemDispositivoRepository.findByChaveHashAndAtivoTrue(calcularHash(chaveBruta)).map(totemDispositivoMapper::toModel);
    }

    private String calcularHash (String chave) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(chave.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(bytes);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 indisponível", ex);
        }
    }
}
