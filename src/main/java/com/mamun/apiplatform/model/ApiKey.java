package com.mamun.apiplatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_keys")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String keyValue;

    private boolean active;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
}