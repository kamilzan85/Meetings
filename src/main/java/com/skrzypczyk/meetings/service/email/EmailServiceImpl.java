package com.skrzypczyk.meetings.service.email;


 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.mail.SimpleMailMessage;
 import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendActivationEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Activation email");
        message.setText("Click here to activate your account: http://localhost:9090/activation?token=" + token);
        message.setTo(to);
        message.setFrom("mateusz.skrzypczyk1@hotmail.com");
        emailSender.send(message);
    }
}
