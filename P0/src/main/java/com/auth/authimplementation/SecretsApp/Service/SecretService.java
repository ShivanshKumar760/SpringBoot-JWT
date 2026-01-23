package com.auth.authimplementation.SecretsApp.Service;

import org.springframework.stereotype.Service;

import com.auth.authimplementation.SecretsApp.SecretMapper.toResponse;
import com.auth.authimplementation.SecretsApp.SecretRepo.SecretRepo;
import com.auth.authimplementation.SecretsApp.SecretsDTO.SecretResponseDTO;
import com.auth.authimplementation.SecretsApp.SecretsEntity.Secrets;

@Service
public class SecretService {
    private final SecretRepo secretRepo;
    public SecretService(SecretRepo secretRepo) {
        this.secretRepo = secretRepo;
    }
    
    public SecretResponseDTO getSecretByValue(String secret) {
        return secretRepo.findBySecret(secret)
                .map(entity -> com.auth.authimplementation.SecretsApp.SecretMapper.toResponse.toResponseViaEntity(entity))
                .orElse(null);
    }

    public SecretResponseDTO getSecretById(long id) {
        return secretRepo.findById(id)
                .map(entity -> com.auth.authimplementation.SecretsApp.SecretMapper.toResponse.toResponseViaEntity(entity))
                .orElse(null);
    }

    public SecretResponseDTO saveSecret(com.auth.authimplementation.SecretsApp.SecretsEntity.Secrets secret) {
        Secrets savedEntity = secretRepo.save(secret);
        return toResponse.toResponseViaEntity(savedEntity);
    }

    //get all secrets without ->
    public java.util.List<SecretResponseDTO> getAllSecrets() {
        java.util.List<Secrets> secrets = secretRepo.findAll();
        java.util.List<SecretResponseDTO> dtoList = new java.util.ArrayList<>();
        for (Secrets secret : secrets) {
            dtoList.add(toResponse.toResponseViaEntity(secret));
        }
        return dtoList;
    }



}
