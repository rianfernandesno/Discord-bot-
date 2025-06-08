package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.ImageProxy;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoCommand implements Command{

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        EmbedBuilder eb = new EmbedBuilder();

        List<Member> mentionedMember = event.getMessage().getMentions().getMembers();
        Member member;

        if (!mentionedMember.isEmpty()){
            member = mentionedMember.getFirst();
        }else {
            member = event.getMember();
        }

        String url  = member.getEffectiveAvatarUrl();
        List<Role> roles = member.getRoles();
        OffsetDateTime joinedAt = member.getTimeJoined();
        OnlineStatus status = member.getOnlineStatus();


        String rolesString = roles.isEmpty() ? "Nenhum" :
                roles.stream()
                        .map(Role::getName)
                        .collect(Collectors.joining(", "));

        eb.setTitle("Informações do usuário")
                .setThumbnail(url)
                .addField("Nome", member.getEffectiveName(), true)
                .addField("Tag", member.getUser().getAsTag(), true)
                .addField("Status", status.getKey().toUpperCase(), true)
                .addField("Entrou em", joinedAt.toLocalDate().toString(), true)
                .addField("Cargos", rolesString, false)
                .setColor(Color.MAGENTA)
                .setFooter("Informações fornecidas por " + event.getAuthor().getName(),
                        event.getAuthor().getEffectiveAvatarUrl());

        event.getChannel().sendMessageEmbeds(eb.build()).queue();

    }
}
