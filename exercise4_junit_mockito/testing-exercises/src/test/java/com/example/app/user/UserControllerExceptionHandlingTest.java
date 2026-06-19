package com.example.app.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Spring Testing Exercise 8: Test Controller Exception Handling (@ControllerAdvice)
@WebMvcTest(UserController.class)
class UserControllerExceptionHandlingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUserStrict_notFound_returns404WithMessage() throws Exception {
        when(userService.getUserByIdOrThrow(123L))
                .thenThrow(new NoSuchElementException("User not found with id: 123"));

        mockMvc.perform(get("/users/strict/123"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
