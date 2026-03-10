package com.mamun.apiplatform.repository;

import com.mamun.apiplatform.model.ApiDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiDefinitionRepository extends JpaRepository<ApiDefinition, Long> {
}