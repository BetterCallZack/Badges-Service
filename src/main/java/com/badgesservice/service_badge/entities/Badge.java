package com.badgesservice.service_badge.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "userBadges") // Évite les problèmes de récursion infinie
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String badgeName;

    @Column(nullable = false, unique = true)
    private String badgeType;

}