package com.auth.authimplementation.SecretsApp.SecretMapper;

import com.auth.authimplementation.SecretsApp.SecretsDTO.SecretResponseDTO;

public class toResponse {
    public static SecretResponseDTO toResponseViaEntity(com.auth.authimplementation.SecretsApp.SecretsEntity.Secrets entity) {
        SecretResponseDTO dto = new SecretResponseDTO();
        dto.setId(entity.getId());
        dto.setSecret(entity.getSecret());
        return dto;
    }    
}
