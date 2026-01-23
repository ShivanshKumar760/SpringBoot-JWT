package com.auth.authimplementation.SecretsApp.SecretMapper;

import com.auth.authimplementation.SecretsApp.SecretsDTO.SecretRequestDTO;
import com.auth.authimplementation.SecretsApp.SecretsEntity.Secrets;

public class toEntity {//SecretRequestDTO to Secrets Entity
    public static Secrets toEntityViaRequest(SecretRequestDTO dto) {
        Secrets entity = new Secrets();
        entity.setSecret(dto.getSecret());
        return entity;
    }
    
}
