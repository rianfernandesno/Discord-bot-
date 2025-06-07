package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.Instant;

public class HelpCommand implements Command{

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("📜 Comandos do Bot\n", null);
        eb.setColor(Color.MAGENTA);

        eb.setDescription("Olá" + event.getAuthor().getAsMention() + "| aqui está os comandos:\n ");
        eb.addField("🏓 !ping", "Responde com 'pong'. Testa se o bot está online.", false);
        eb.addField("🗣️ !say", "Repete o que você digitar.", false);
        eb.addField("🧹 !clear", "Apaga mensagens do chat (uso limitado).", false);
        eb.addField("👢 !kick", "Expulsa um usuário do servidor.", false);
        eb.addField("⛔ !ban", "Bane um usuário do servidor.", false);
        eb.addField("✅ !unban", "Remove o banimento de um usuário.", false);


        eb.setTimestamp(Instant.now());
        eb.setFooter("Use os comandos acima para interagir comigo!", event.getJDA().getSelfUser().getAvatarUrl());
        event.getChannel().sendMessageEmbeds(eb.build()).queue();
    }
}
