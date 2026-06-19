package com.example.app.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Mocking a Service Dependency in a Controller Test (Mockito-Spring sheet Exercise 1)
// Testing a REST Controller with MockMvc (Spring Testing sheet Exercise 3)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser() throws Exception {
        User user = new User(1L, "Alice");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Alice\"}"));
    }

    @Test
    void testGetUser_notFound_returnsNullBodyButOk() throws Exception {
        when(userService.getUserById(99L)).thenReturn(null);

        mockMvc.perform(get("/users/99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
