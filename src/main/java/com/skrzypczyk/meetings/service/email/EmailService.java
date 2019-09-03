package com.skrzypczyk.meetings.service.email;

public interface EmailService {
    void sendActivationEmail(String to, String token);
}
