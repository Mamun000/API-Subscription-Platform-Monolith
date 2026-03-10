package com.mamun.apiplatform.service;

import com.mamun.apiplatform.repository.UsageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final UsageLogRepository usageLogRepository;

    public Map<String, Object> getPlatformStats() {

        Map<String, Object> stats = new HashMap<>();

        Long totalRequests = usageLogRepository.countTotalRequests();

        List<Object[]> endpointStats =
                usageLogRepository.countRequestsPerEndpoint();

        stats.put("totalRequests", totalRequests);
        stats.put("endpointUsage", endpointStats);

        return stats;
    }
}