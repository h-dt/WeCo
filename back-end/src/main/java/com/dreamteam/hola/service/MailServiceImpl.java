package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.MailDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class MailServiceImpl {

    private final JavaMailSender mailSender;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.subject}")
    private String adminSubject;

    public void sendMail(MailDto mailDto) throws MessagingException {
        StringBuilder text = new StringBuilder();
        text.append("<html> <body>");
        text.append("<h1>평가: ").append(mailDto.getRating()).append("</h1><br>");
        text.append("<p>평가 내용: ").append(mailDto.getEvaluateText()).append("</p>");
        text.append("</body> </html>");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(adminSubject);
        helper.setTo(adminEmail);
        helper.setText(text.toString(), true);

        mailSender.send(message);
    }
}
