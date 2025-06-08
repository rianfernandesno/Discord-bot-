package com.yuicottrill.discordbot.commands;

import com.yuicottrill.discordbot.service.GuildDataService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetWelcomeChannel implements Command{
    @Autowired
    private GuildDataService service;

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String welcomeMessage = args[0];

        service.saveWelcome(event.getGuild(), welcomeMessage);
        event.getChannel().sendMessage("Mensagem de bem vindo setado para o canal do id: " + welcomeMessage).queue();
    }
}
