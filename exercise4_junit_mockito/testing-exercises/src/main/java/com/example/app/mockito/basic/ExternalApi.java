package com.example.app.mockito.basic;

// Mockito Basic Exercise 1-3, 5-6
public interface ExternalApi {
    String getData(String param);

    // Exercise 6: Verifying Interaction Order - second method to sequence calls against
    void log(String message);
}
