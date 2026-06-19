package com.example.app.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Spring Testing Exercise 7: Test Custom Repository Query
// @DataJpaTest spins up an in-memory DB (H2) and only the JPA layer
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByName() {
        userRepository.save(new User(null, "Diana"));
        userRepository.save(new User(null, "Diana"));
        userRepository.save(new User(null, "Eve"));

        List<User> result = userRepository.findByName("Diana");

        assertEquals(2, result.size());
    }
}
