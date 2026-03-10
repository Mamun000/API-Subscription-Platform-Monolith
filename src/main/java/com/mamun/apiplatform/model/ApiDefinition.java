package com.mamun.apiplatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_definitions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String baseUrl;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private boolean active;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}