package ua.iasa.happyfridge.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

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

    @SneakyThrows
    public void sendHtml(String body, String subject, String to){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        byte[] ptext = body.getBytes(ISO_8859_1);
        String value = new String(ptext, UTF_8);
        mimeMessage.setContent(value, "text/html");
        helper.setTo(to);
        helper.setFrom("happyfridge@gmail.com");
        mimeMessage.setSubject(subject, "UTF-8");
        mailSender.send(mimeMessage);
    }
}
