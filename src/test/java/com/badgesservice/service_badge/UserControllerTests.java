package com.badgesservice.service_badge;

import com.badgesservice.service_badge.controllers.UserController;
import com.badgesservice.service_badge.entities.User;
import com.badgesservice.service_badge.services.UserService;
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

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        Mockito.when(userService.findAll()).thenReturn(Collections.singletonList(new User()));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\", \"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User created successfully"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteById(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));
    }
}
