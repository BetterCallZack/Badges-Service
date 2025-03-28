package com.badgesservice.service_badge.controllers;

import com.badgesservice.service_badge.entities.Badge;
import com.badgesservice.service_badge.services.BadgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badgeMangment")
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    // Créer un nouveau badge
    // ne fonctionne pas car elle recoit pas de badge mes un json
    @PostMapping
    public ResponseEntity<Badge> createBadge(@RequestBody Badge badge) {

        Badge createdBadge = badgeService.save(badge);
        return new ResponseEntity<>(createdBadge, HttpStatus.CREATED);
    }

    // Récupérer tous les badges
    @GetMapping
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> badges = badgeService.findAll();
        if(badges.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }

    // Récupérer un badge par ID
    @GetMapping("/{badgeId}")
    public ResponseEntity<Badge> getBadgeById(@PathVariable Long badgeId) {
        Badge badge = badgeService.findById(badgeId);
        return new ResponseEntity<>(badge, HttpStatus.OK);
    }

    // Supprimer un badge par ID
    @DeleteMapping("/{badgeId}")
    public ResponseEntity<Void> deleteBadge(@PathVariable Long badgeId) {
        badgeService.deleteById(badgeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // update
    @PutMapping("/{badgeId}")
    public ResponseEntity<Badge> updateBadge(@PathVariable Long badgeId, @RequestBody Badge badgeDetails) {
        Badge updatedBadge = badgeService.update(badgeId, badgeDetails);
        if (updatedBadge == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedBadge);
    }


}
