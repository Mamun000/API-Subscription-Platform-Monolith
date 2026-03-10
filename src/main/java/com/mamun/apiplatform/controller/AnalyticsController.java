package com.mamun.apiplatform.controller;

import com.mamun.apiplatform.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @PreAuthorize("hasRole('DEVELOPER')")
    @GetMapping("/platform")
    public Map<String, Object> platformStats() {
        return analyticsService.getPlatformStats();
    }
}