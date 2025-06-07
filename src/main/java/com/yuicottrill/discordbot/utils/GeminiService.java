package com.yuicottrill.discordbot.utils;

import com.yuicottrill.discordbot.dtos.GeminiRequest;
import com.yuicottrill.discordbot.dtos.GeminiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GeminiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gemini.api.key}")
    private String apikey;

    private static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

    public GeminiResponse generateContent(String prompt){
        GeminiRequest.Part part = new GeminiRequest.Part();
        part.setText(prompt);

        GeminiRequest.Content content = new GeminiRequest.Content();
        content.setParts(List.of(part));

        GeminiRequest request = new GeminiRequest();
        request.setContents(List.of(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String uriWithKey = URL + "?key=" + apikey;

        HttpEntity<GeminiRequest> entity = new HttpEntity<>(request, headers);


        ResponseEntity<GeminiResponse> response = restTemplate.exchange(
                uriWithKey,
                HttpMethod.POST,
                entity,
                GeminiResponse.class
        );

        return response.getBody();
    }
}
