package com.auth.authimplementation.SecretsApp.SecretRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.authimplementation.SecretsApp.SecretsEntity.Secrets;

@Repository
public interface SecretRepo extends JpaRepository<com.auth.authimplementation.SecretsApp.SecretsEntity.Secrets, Long> {
    Optional<Secrets> findBySecret(String secret);
}
