package com.example.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private ThirdPartyPaymentClient thirdPartyPaymentClient;

    @PostMapping("/process")
    public String processPayment(@RequestParam String paymentId,
                                  @RequestParam double amount) {
        return thirdPartyPaymentClient.processPayment(paymentId, amount);
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Payment Service is UP and running with Circuit Breaker enabled!";
    }
}
