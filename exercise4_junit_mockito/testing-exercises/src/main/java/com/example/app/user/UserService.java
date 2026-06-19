package com.example.app.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        logger.debug("Looking up user with id={}", id);
        return userRepository.findById(id).orElse(null);
    }

    // Exercise Sheet 3 - Exercise 6: Service throws when user missing
    public User getUserByIdOrThrow(Long id) {
        logger.debug("Looking up user with id={} (must exist)", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("User with id={} not found", id);
                    return new NoSuchElementException("User not found with id: " + id);
                });
    }

    public User saveUser(User user) {
        logger.info("Saving user: {}", user.getName());
        return userRepository.save(user);
    }
}
