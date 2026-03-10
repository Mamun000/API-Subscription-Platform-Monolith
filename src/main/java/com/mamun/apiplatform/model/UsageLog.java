package com.mamun.apiplatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usage_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiKey;

    private String endpoint;

    private int statusCode;

    private LocalDateTime timestamp;
}