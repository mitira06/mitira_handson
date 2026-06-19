package com.example.app.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Mocking-Spring sheet Exercise 3 (using the hinted annotations):
// Full Spring context is loaded, but UserService is replaced with a Mockito mock via @MockBean.
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceMockedIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser_withMockedServiceInFullContext() throws Exception {
        User user = new User(7L, "Grace");
        when(userService.getUserById(7L)).thenReturn(user);

        mockMvc.perform(get("/users/7"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":7,\"name\":\"Grace\"}"));
    }
}
