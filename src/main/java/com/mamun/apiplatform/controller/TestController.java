package com.mamun.apiplatform.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/v1/test")
    public String securedEndpoint() {
        return "Authenticated user!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/v1/admin")
    public String adminOnly() {
        return "Admin access granted!";
    }

    @PreAuthorize("hasRole('DEVELOPER')")
    @GetMapping("/api/v1/developer")
    public String developerOnly() {
        return "Developer access granted!";
    }

    @PreAuthorize("hasRole('CONSUMER')")
    @GetMapping("/api/v1/consumer")
    public String consumerOnly() {
        return "Consumer access granted!";
    }
}