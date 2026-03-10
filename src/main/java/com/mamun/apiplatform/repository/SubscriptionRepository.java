package com.mamun.apiplatform.repository;

import com.mamun.apiplatform.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByUserIdAndApiDefinitionId(Long userId, Long apiId);
}