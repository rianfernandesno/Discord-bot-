package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class KickCommand implements Command{

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        List<Member> mentionedMember = event.getMessage().getMentions().getMembers();

        if(!mentionedMember.isEmpty()){
            Member target = mentionedMember.get(0);

            event.getGuild()
                    .kick(target)
                    .reason("kick test")
                    .queue(
                            success -> event.getChannel().sendMessage("Usuário kickado com sucesso " + target).queue(),
                            error -> event.getChannel().sendMessage("Algo inesperado ocorreu: " + error.getMessage()).queue()
                    );
        }

    }
}
