package com.mamun.apiplatform.dto;

import com.mamun.apiplatform.model.PlanType;
import lombok.Data;

@Data
public class SubscribeRequest {
    private Long apiId;
    private PlanType planType;
}