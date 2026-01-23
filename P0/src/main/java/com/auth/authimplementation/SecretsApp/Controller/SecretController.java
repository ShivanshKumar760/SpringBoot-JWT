package com.auth.authimplementation.SecretsApp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authimplementation.SecretsApp.Service.SecretService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.auth.authimplementation.SecretsApp.SecretsDTO.SecretResponseDTO;
import com.auth.authimplementation.SecretsApp.SecretsDTO.SecretRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/secrets")
public class SecretController {
    private final SecretService secretService;
    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping("/save")
    public SecretResponseDTO saveSecret(@RequestBody SecretRequestDTO requestDTO) {
        com.auth.authimplementation.SecretsApp.SecretsEntity.Secrets secretEntity = new com.auth.authimplementation.SecretsApp.SecretsEntity.Secrets();
        secretEntity.setSecret(requestDTO.getSecret());
        return secretService.saveSecret(secretEntity);
    }

    @GetMapping("/all")
    public java.util.List<SecretResponseDTO> getAllSecrets() {
        return secretService.getAllSecrets();
    }
    
    
}
