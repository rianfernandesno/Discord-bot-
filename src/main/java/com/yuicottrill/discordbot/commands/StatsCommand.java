package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.OffsetDateTime;

public class StatsCommand implements Command{

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder eb = new EmbedBuilder();


        Guild guild = event.getGuild();

        String name = guild.getName();
        String iconUrl = guild.getIconUrl();
        int countMember = guild.getMemberCount();
        Member owner = guild.getOwner();
        OffsetDateTime created = guild.getTimeCreated();

        eb.setTitle("📊 Estatísticas do servidor")
                .setThumbnail(iconUrl)
                .addField("Nome do servidor", name, false)
                .addField("Dono", owner.getEffectiveName() + "#" + owner.getUser().getDiscriminator(), false)
                .addField("Membros", String.valueOf(countMember), false)
                .addField("Criado em", created.toLocalDate().toString(), false)
                .setColor(Color.MAGENTA)
                .setFooter("Informações atualizadas", iconUrl);

        event.getChannel().sendMessageEmbeds(eb.build()).queue();

    }
}
