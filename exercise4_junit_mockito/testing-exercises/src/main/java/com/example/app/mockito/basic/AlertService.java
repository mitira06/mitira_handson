package com.example.app.mockito.basic;

public class AlertService {

    private final Notifier notifier;

    public AlertService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void sendAlert(String message) {
        notifier.notify(message);
    }
}
