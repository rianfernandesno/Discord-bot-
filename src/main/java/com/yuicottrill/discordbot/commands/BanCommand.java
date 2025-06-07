package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BanCommand implements Command{

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        List<Member> members = event.getMessage().getMentions().getMembers();

        if (!members.isEmpty()){

            Member member = members.getFirst();

            event.getGuild()
                    .ban(member.getUser(), 7, TimeUnit.DAYS)
                    .reason("Banido")
                    .queue(
                            success -> event.getChannel().sendMessage("Usuário banido com sucesso ").queue(),
                            error -> event.getChannel().sendMessage("Algo inesperado ocorreu: " + error.getMessage()).queue()
                    );

        }
    }
}
