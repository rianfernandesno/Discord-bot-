package com.yuicottrill.discordbot.utils;

import com.yuicottrill.discordbot.models.entities.GuildData;
import com.yuicottrill.discordbot.repositories.GuildDataRepository;
import com.yuicottrill.discordbot.service.GuildDataService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
@Component
public class GuildEventListener extends ListenerAdapter {

    @Autowired
    private GuildDataService guildService;

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        guildService.saveGuild(event.getGuild());
        System.out.println("Bot adicionado ao servidor: " + event.getGuild().getName());
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        guildService.deleteGuildById(event.getGuild().getId());
        System.out.println("Bot removido do servidor: " + event.getGuild().getName());
    }
}

