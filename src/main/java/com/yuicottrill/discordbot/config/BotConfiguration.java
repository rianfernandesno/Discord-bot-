package com.yuicottrill.discordbot.config;

import com.yuicottrill.discordbot.utils.MemberJoinOrLeaveListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.dv8tion.jda.api.JDABuilder;

@Configuration
public class BotConfiguration {
    @Value("${DISCORD_TOKEN}")
    String token;

    @Bean
    public JDA Connection() throws Exception{
        return JDABuilder.createDefault(token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new MyListener())
                .addEventListeners(new MemberJoinOrLeaveListener())
                .build()
                .awaitReady();
    }


}
