package com.mamun.apiplatform.dto;

import lombok.Data;

@Data
public class CreateApiRequest {
    private String name;
    private String description;
    private String baseUrl;
    private String category;
}