package com.yuicottrill.discordbot.commands;

import com.yuicottrill.discordbot.service.GuildDataService;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetLeaveMessageChannel  implements Command{
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

        String leaveMessage = args[0];

        service.saveLeaveChannel(event.getGuild(), leaveMessage);
        event.getChannel().sendMessage("Setado a message de saida para: " + leaveMessage).queue();
    }
}
