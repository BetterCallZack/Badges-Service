package com.badgesservice.service_badge.repositories;

import com.badgesservice.service_badge.entities.Badge;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
