package ua.iasa.happyfridge.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendEmail(String body, String subject, String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setTo(to);
        mailMessage.setFrom("happyfridge@gmail.com");
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }
}
