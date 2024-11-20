package com.bgyato.fibonacci_time_backend.service;

import com.bgyato.fibonacci_time_backend.service.interfaces.IFibonacciService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IFibonacciService service;


    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(service.listFibonacciSeries().toString(), true);
        helper.setFrom("andresfyatem@gmail.com");

        mailSender.send(message);
    }

}
