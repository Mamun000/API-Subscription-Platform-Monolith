package com.mamun.apiplatform.service;

import com.mamun.apiplatform.model.UsageLog;
import com.mamun.apiplatform.repository.UsageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsageLogService {

    private final UsageLogRepository usageLogRepository;

    public void logRequest(String apiKey, String endpoint, int statusCode) {

        UsageLog log = UsageLog.builder()
                .apiKey(apiKey)
                .endpoint(endpoint)
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now())
                .build();

        usageLogRepository.save(log);
    }
}