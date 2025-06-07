package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UnbanCommand implements Command{

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        String userId = args[0];

        event.getJDA().retrieveUserById(userId).queue(
                user -> {
                    event.getGuild().unban(user).queue(
                            success -> event.getChannel().sendMessage("Usuário desbanido: " + user.getAsTag()).queue(),
                            error -> event.getChannel().sendMessage("Erro ao desbanir: " + error.getMessage()).queue()
                    );
                },
                error -> event.getChannel().sendMessage("Usuário com ID `" + userId + "` não encontrado.").queue()
        );
    }

}
