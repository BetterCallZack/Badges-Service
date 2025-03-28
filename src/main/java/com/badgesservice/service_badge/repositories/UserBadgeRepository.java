package com.badgesservice.service_badge.repositories;

import com.badgesservice.service_badge.entities.Badge;
import com.badgesservice.service_badge.entities.User;
import com.badgesservice.service_badge.entities.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {

    @Query("SELECT ub.user FROM UserBadge ub WHERE ub.badge.id = :badgeId")
    List<User> findUsersByBadgeId(@Param("badgeId") Long badgeId);

    @Query("SELECT ub.badge FROM UserBadge ub WHERE ub.user.id = :userId")
    List<Badge> findBadgesByUserId(@Param("userId") Long userId);

    Optional<UserBadge> findByUserAndBadge(User user, Badge badge);

    boolean existsByUserAndBadge(User user, Badge badge);
}