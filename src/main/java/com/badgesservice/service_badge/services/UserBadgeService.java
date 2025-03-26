package com.badgesservice.service_badge.services;

import com.badgesservice.service_badge.entities.Badge;
import com.badgesservice.service_badge.entities.User;
import com.badgesservice.service_badge.entities.UserBadge;
import com.badgesservice.service_badge.repositories.BadgeRepository;
import com.badgesservice.service_badge.repositories.UserBadgeRepository;
import com.badgesservice.service_badge.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBadgeService {

    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;

    public UserBadgeService(UserRepository userRepository, BadgeRepository badgeRepository, UserBadgeRepository userBadgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
        this.userBadgeRepository = userBadgeRepository;
    }

    public List<User> findUsersByBadgeId(Long badgeId) {
        return userBadgeRepository.findUsersByBadgeId(badgeId);
    }

    public List<Badge> getBadgesByUserId(Long userId) {
        return userBadgeRepository.findBadgesByUserId(userId);
    }


    public void addBadgeToUser(Long userId, Long badgeId) {
        User user;
        Badge badge;
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User ID " + userId + " does not exist.");
        }
        if (!badgeRepository.existsById(badgeId)) {
            throw new IllegalArgumentException("Badge ID " + badgeId + " does not exist.");
        }

        badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new RuntimeException("Badge not found"));
        user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserBadge userBadge = new UserBadge(user, badge);

        userBadgeRepository.save(new UserBadge(user, badge));
    }

    public void removeBadgeFromUser(Long userId, Long badgeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new RuntimeException("Badge not found"));

        UserBadge userBadge = userBadgeRepository.findByUserAndBadge(user, badge)
                .orElseThrow(() -> new RuntimeException("UserBadge not found"));

        userBadgeRepository.delete(userBadge);
    }
}