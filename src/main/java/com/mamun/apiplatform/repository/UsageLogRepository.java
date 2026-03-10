package com.mamun.apiplatform.repository;

import com.mamun.apiplatform.model.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {
    @Query("""
SELECT COUNT(u)
FROM UsageLog u
WHERE u.endpoint = :endpoint
""")
    Long countByEndpoint(String endpoint);

    @Query("""
SELECT COUNT(u)
FROM UsageLog u
""")
    Long countTotalRequests();

    @Query("""
SELECT u.endpoint, COUNT(u)
FROM UsageLog u
GROUP BY u.endpoint
""")
    List<Object[]> countRequestsPerEndpoint();
}