package com.mamun.apiplatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "api_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlanType planType;

    private int requestLimit;

    private LocalDateTime subscribedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "api_id")
    private ApiDefinition apiDefinition;
}