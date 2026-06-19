package com.example.app.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Mocking a Repository in a Service Test (appears in both the Mockito-Spring
// sheet Exercise 2 and the Spring Testing sheet Exercise 2)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById_found() {
        User user = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        User result = userService.getUserById(99L);

        assertNull(result);
    }

    // Spring Testing Exercise 6: Test Service Exception Handling (missing user)
    @Test
    void testGetUserByIdOrThrow_throwsWhenMissing() {
        when(userRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> userService.getUserByIdOrThrow(42L));
    }

    @Test
    void testSaveUser() {
        User user = new User(null, "Bob");
        User savedUser = new User(1L, "Bob");
        when(userRepository.save(user)).thenReturn(savedUser);

        User result = userService.saveUser(user);

        assertEquals(1L, result.getId());
        verify(userRepository).save(user);
    }
}
