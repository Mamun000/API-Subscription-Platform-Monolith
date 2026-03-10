package com.mamun.apiplatform.controller;

import com.mamun.apiplatform.dto.CreateApiRequest;
import com.mamun.apiplatform.model.ApiDefinition;
import com.mamun.apiplatform.service.ApiDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apis")
@RequiredArgsConstructor
public class ApiDefinitionController {

    private final ApiDefinitionService apiService;

    @PreAuthorize("hasRole('DEVELOPER')")
    @PostMapping
    public ApiDefinition createApi(@RequestBody CreateApiRequest request) {
        return apiService.createApi(request);
    }

    @GetMapping
    public List<ApiDefinition> getAllApis() {
        return apiService.getAllApis();
    }

    @GetMapping("/{id}")
    public ApiDefinition getApi(@PathVariable Long id) {
        return apiService.getApi(id);
    }
}