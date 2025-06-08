package com.yuicottrill.discordbot.utils;

import com.yuicottrill.discordbot.models.entities.GuildData;
import com.yuicottrill.discordbot.repositories.GuildDataRepository;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class GuildJoinOrLeaveListener extends ListenerAdapter {

    @Autowired
    private GuildDataRepository repository;

    @Override
    public  void onGuildJoin(GuildJoinEvent event){
        Guild guild = event.getGuild();

        GuildData guildData = new GuildData(
                guild.getId(),
                guild.getName(),
                guild.getOwnerId(),
                guild.getMemberCount(),
                Instant.now(),
                guild.getTimeCreated().toInstant(),
                guild.getIconUrl(),
                null,
                null,
                null,
                null
        );

        if (!repository.existsById(guildData.getId())){
            repository.save(guildData);
        }

        System.out.println("Bot adicionado ao servidor: " + guild.getName());
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event){
        String guildId = event.getGuild().getId();

        repository.deleteById(guildId);

        System.out.println("Bot removido do servidor: " + event.getGuild().getName());
    }
}
