package com.badgesservice.service_badge.controllers;

import com.badgesservice.service_badge.entities.Badge;
import com.badgesservice.service_badge.entities.User;
import com.badgesservice.service_badge.services.UserBadgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userBadge")
public class UserBadgeController {

    private final UserBadgeService userBadgeService;

    public UserBadgeController(UserBadgeService userBadgeService) {
        this.userBadgeService = userBadgeService;
    }

    @GetMapping("/users/{badgeId}")
    public ResponseEntity<List<User>> findUsersByBadgeId(@PathVariable Long badgeId) {
        List<User> users = userBadgeService.findUsersByBadgeId(badgeId);

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/badges/{userId}")
    public ResponseEntity<List<Badge>> findBadgesByUserId(@PathVariable Long userId) {
        List<Badge> badges = userBadgeService.getBadgesByUserId(userId);

        if (badges.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(badges);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addBadgeToUser(@RequestParam Long userId, @RequestParam Long badgeId) {
        userBadgeService.addBadgeToUser(userId, badgeId);
        return ResponseEntity.ok("Badge added successfully!");
    }

    @DeleteMapping("/{userId}/{badgeId}")
    public ResponseEntity<String> removeBadgeFromUser(@PathVariable Long userId, @PathVariable Long badgeId) {
        userBadgeService.removeBadgeFromUser(userId, badgeId);
        return ResponseEntity.ok("Badge removed successfully!");
    }

}