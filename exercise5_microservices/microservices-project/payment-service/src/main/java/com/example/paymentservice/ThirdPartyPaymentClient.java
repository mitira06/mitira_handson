package com.example.paymentservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyPaymentClient {

    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyPaymentClient.class);

    @CircuitBreaker(name = "paymentCircuitBreaker", fallbackMethod = "paymentFallback")
    public String processPayment(String paymentId, double amount) {
        logger.info("Processing payment {} for amount {}", paymentId, amount);
        // Simulating slow third-party API call
        simulateSlowApi();
        return "Payment " + paymentId + " processed successfully for amount: " + amount;
    }

    public String paymentFallback(String paymentId, double amount, Exception ex) {
        logger.error("Payment fallback triggered for paymentId: {} - Reason: {}", paymentId, ex.getMessage());
        return "Payment service is currently unavailable. Please try again later. PaymentId: " + paymentId;
    }

    private void simulateSlowApi() {
        try {
            // Simulating slow response - sometimes fails
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Payment API timeout!");
        }
    }
}
