package com.yuicottrill.discordbot.commands;

import com.yuicottrill.discordbot.service.GuildDataService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetAutoRole implements Command{
    @Autowired
    private GuildDataService service;

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        String autoRole = args[0];

        service.saveAutoRole(event.getGuild(), autoRole);
        event.getChannel().sendMessage("Setado a role do id: " + autoRole).queue();
    }
}
