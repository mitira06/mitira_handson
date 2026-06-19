package com.example.app.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Exercise Sheet 3 - Exercise 7: Custom repository query
    List<User> findByName(String name);
}
