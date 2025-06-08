package com.yuicottrill.discordbot.commands;

import com.yuicottrill.discordbot.service.GuildDataService;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetWelcomeChannel implements Command{
    @Autowired
    private GuildDataService service;

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        if (!event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
            event.getChannel().sendMessage("Você não tem acesso a esse comando!").queue();
            return; // Impede a continuação do método se não tiver permissão
        }

        if (args.length == 0) {
            event.getChannel().sendMessage("Você precisa fornecer o ID da role.").queue();
            return;
        }

        String welcomeMessage = args[0];

        service.saveWelcome(event.getGuild(), welcomeMessage);
        event.getChannel().sendMessage("Mensagem de bem vindo setado para o canal do id: " + welcomeMessage).queue();
    }
}
