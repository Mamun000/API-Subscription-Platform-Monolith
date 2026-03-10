package com.mamun.apiplatform.controller;

import com.mamun.apiplatform.dto.SubscribeRequest;
import com.mamun.apiplatform.model.ApiKey;
import com.mamun.apiplatform.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PreAuthorize("hasRole('CONSUMER')")
    @PostMapping
    public ApiKey subscribe(@RequestBody SubscribeRequest request) {
        return subscriptionService.subscribe(request);
    }
}