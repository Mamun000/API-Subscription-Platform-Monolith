package com.mamun.apiplatform.repository;

import com.mamun.apiplatform.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    Optional<ApiKey> findByKeyValue(String keyValue);

    Optional<ApiKey> findBySubscriptionId(Long subscriptionId);
}