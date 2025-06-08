package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class MuteCommand implements Command{


    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        List<Member> members = event.getMessage().getMentions().getMembers();

        if (!members.isEmpty()){

            if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)){

                event.getChannel().sendMessage("Você não tem permissão de mutar usuários").queue();
                throw new IllegalArgumentException("Não pode mutar usuários");
            }

            Member member = members.getFirst();

            List<TextChannel> channels = event.getGuild().getTextChannels();

            for (TextChannel channel : channels){

                channel.upsertPermissionOverride(member)
                        .deny(Permission.MESSAGE_SEND)
                        .queue();
            }

            Role mutedRole = event.getGuild()
                            .getRolesByName("Mutado!", true)
                                    .stream()
                                            .findFirst()
                                                    .orElse(null);

            event.getGuild()
                            .addRoleToMember(member, mutedRole)
                    .queue();
            event.getChannel().sendMessage("O usuário " + member.getEffectiveName() + " foi mutado em todos os canais!").queue();

        }

    }
}
