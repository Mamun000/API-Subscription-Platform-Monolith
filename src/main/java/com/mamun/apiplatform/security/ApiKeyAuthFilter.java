package com.mamun.apiplatform.security;

import com.mamun.apiplatform.model.ApiKey;
import com.mamun.apiplatform.repository.ApiKeyRepository;
import com.mamun.apiplatform.service.RateLimiterService;
import com.mamun.apiplatform.service.UsageLogService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final ApiKeyRepository apiKeyRepository;
    private final RateLimiterService rateLimiterService;
    private final UsageLogService usageLogService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String apiKeyHeader = request.getHeader("X-API-KEY");

        if (apiKeyHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        ApiKey apiKey = apiKeyRepository
                .findByKeyValue(apiKeyHeader)
                .orElse(null);

        if (apiKey == null || !apiKey.isActive()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid API Key");
            return;
        }

        int limit = apiKey.getSubscription().getRequestLimit();

        boolean allowed = rateLimiterService.isAllowed(apiKeyHeader, limit);

        if (!allowed) {
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded");
            return;
        }

        filterChain.doFilter(request, response);

        usageLogService.logRequest(
                apiKeyHeader,
                request.getRequestURI(),
                response.getStatus()
        );
    }
}