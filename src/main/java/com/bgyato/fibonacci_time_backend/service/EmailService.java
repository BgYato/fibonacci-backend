package com.bgyato.fibonacci_time_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {


    @Value("${mailgun.api-key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    private final RestTemplate restTemplate;

    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendEmail(String to, String subject, String text) {
        String url = "https://api.mailgun.net/v3/" + domain + "/messages";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("from", "USER@sandboxb867180e5c8841f3bd0a6a23a11f5d76.mailgun.org");
        body.add("to", to);
        body.add("subject", subject);
        body.add("text", text);

        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodeAuth(apiKey));

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    private String encodeAuth(String apiKey) {
        String auth = "api:" + apiKey;
        return new String(java.util.Base64.getEncoder().encode(auth.getBytes()));
    }

}
