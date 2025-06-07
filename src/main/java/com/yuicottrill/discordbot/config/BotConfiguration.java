package com.yuicottrill.discordbot.config;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import net.dv8tion.jda.api.JDABuilder;

@Configuration
public class BotConfiguration {
    @Value("${DISCORD_TOKEN}")
    String token;

    @PostConstruct
    public void Connection() throws Exception{
        JDA api = JDABuilder.createDefault(token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new MyListener())
                .build();
        api.awaitReady();
    }

}
