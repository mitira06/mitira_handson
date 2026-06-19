package com.example.app.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

// Mocking-Spring sheet Exercise 3: Integration test for Spring Boot app
// Spring Testing sheet Exercise 4: Integration test (controller -> service -> repository -> real H2 DB)
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFullFlow_createThenFetchUser() throws Exception {
        // Act: create a user through the real controller -> service -> repository -> H2 DB
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\":\"Frank\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Frank")));

        // Find the id that was generated
        Long savedId = userRepository.findByName("Frank").get(0).getId();

        // Act + Assert: fetch the same user back through the real endpoint
        mockMvc.perform(get("/users/" + savedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Frank")));
    }
}
