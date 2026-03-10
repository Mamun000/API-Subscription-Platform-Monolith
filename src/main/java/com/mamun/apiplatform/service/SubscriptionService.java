package com.mamun.apiplatform.service;

import com.mamun.apiplatform.dto.SubscribeRequest;
import com.mamun.apiplatform.model.*;
import com.mamun.apiplatform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ApiDefinitionRepository apiRepository;
    private final UserRepository userRepository;
    private final ApiKeyRepository apiKeyRepository;

    public ApiKey subscribe(SubscribeRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ApiDefinition api = apiRepository.findById(request.getApiId())
                .orElseThrow(() -> new RuntimeException("API not found"));

        Subscription existingSubscription =
                subscriptionRepository
                        .findByUserIdAndApiDefinitionId(user.getId(), api.getId())
                        .orElse(null);

        if (existingSubscription != null) {

            ApiKey existingKey = apiKeyRepository
                    .findBySubscriptionId(existingSubscription.getId())
                    .orElseThrow(() -> new RuntimeException("API key missing"));

            return existingKey;
        }

        int limit = switch (request.getPlanType()) {
            case FREE -> 100;
            case PRO -> 1000;
            case ENTERPRISE -> 10000;
        };

        Subscription subscription = Subscription.builder()
                .planType(request.getPlanType())
                .requestLimit(limit)
                .subscribedAt(LocalDateTime.now())
                .user(user)
                .apiDefinition(api)
                .build();

        subscriptionRepository.save(subscription);

        String generatedKey = UUID.randomUUID().toString();

        ApiKey apiKey = ApiKey.builder()
                .keyValue(generatedKey)
                .active(true)
                .createdAt(LocalDateTime.now())
                .subscription(subscription)
                .build();

        return apiKeyRepository.save(apiKey);
    }
}