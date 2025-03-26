package com.badgesservice.service_badge.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"user", "badge"}) // Évite la récursion infinie
public class UserBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge;


    public UserBadge(User user, Badge badge) {
        this.user = user;
        this.badge = badge;
    }
}
