package com.badgesservice.service_badge;

import com.badgesservice.service_badge.controllers.UserBadgeController;
import com.badgesservice.service_badge.entities.Badge;
import com.badgesservice.service_badge.entities.User;
import com.badgesservice.service_badge.services.UserBadgeService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserBadgeController.class)
public class UserBadgeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserBadgeService userBadgeService;

    @Test
    public void testFindUsersByBadgeId() throws Exception {
        Mockito.when(userBadgeService.findUsersByBadgeId(1L))
                .thenReturn(Collections.singletonList(new User()));

        mockMvc.perform(get("/userBadge/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindBadgesByUserId() throws Exception {
        Mockito.when(userBadgeService.getBadgesByUserId(1L))
                .thenReturn(Collections.singletonList(new Badge()));

        mockMvc.perform(get("/userBadge/badges/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddBadgeToUser() throws Exception {
        mockMvc.perform(post("/userBadge/create/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Badge added successfully!"));
    }

    @Test
    public void testRemoveBadgeFromUser() throws Exception {
        mockMvc.perform(delete("/userBadge/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Badge removed successfully!"));
    }
}