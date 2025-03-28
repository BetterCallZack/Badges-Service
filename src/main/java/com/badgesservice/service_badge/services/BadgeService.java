package com.badgesservice.service_badge.services;

import com.badgesservice.service_badge.entities.Badge;
import com.badgesservice.service_badge.repositories.BadgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;

    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    public Badge save(Badge badge) {
        return badgeRepository.save(badge);
    }

    public List<Badge> findAll() {
        return badgeRepository.findAll();
    }

    public Badge findById(Long id) {
        return badgeRepository.findById(id).orElse(null);
    }


    public  void deleteById(Long id) {
        badgeRepository.deleteById(id);
    }

    public Badge update(Long badgeId, Badge badgeDetails) {
        return badgeRepository.findById(badgeId).map(badge -> {
            badge.setBadgeName(badgeDetails.getBadgeName());
            badge.setDescription(badgeDetails.getDescription());
            badge.setBadgeType(badgeDetails.getBadgeType());
            return badgeRepository.save(badge);
        }).orElse(null);
    }

}
