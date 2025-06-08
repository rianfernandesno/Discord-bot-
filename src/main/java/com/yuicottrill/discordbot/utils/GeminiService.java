package com.yuicottrill.discordbot.utils;

import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;

@Service
public class GeminiService {

    private  final Client client;

    public GeminiService(@Value("${gemini.api.key}") String apiKey) {
        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String generateContext(String prompt){

        String model = "gemini-2.0-flash-001";
        GenerateContentResponse response =
                client.models.generateContent(model, prompt, null);

        return response.text();
    }



}
