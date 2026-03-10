package com.mamun.apiplatform.service;

import com.mamun.apiplatform.dto.CreateApiRequest;
import com.mamun.apiplatform.model.ApiDefinition;
import com.mamun.apiplatform.model.User;
import com.mamun.apiplatform.repository.ApiDefinitionRepository;
import com.mamun.apiplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiDefinitionService {

    private final ApiDefinitionRepository apiRepository;
    private final UserRepository userRepository;

    public ApiDefinition createApi(CreateApiRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ApiDefinition api = ApiDefinition.builder()
                .name(request.getName())
                .description(request.getDescription())
                .baseUrl(request.getBaseUrl())
                .category(request.getCategory())
                .active(true)
                .createdAt(LocalDateTime.now())
                .owner(user)
                .build();

        return apiRepository.save(api);
    }

    public List<ApiDefinition> getAllApis() {
        return apiRepository.findAll();
    }

    public ApiDefinition getApi(Long id) {
        return apiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("API not found"));
    }
}