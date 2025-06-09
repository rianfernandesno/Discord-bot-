package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class HelpCommand extends ListenerAdapter implements Command {

    private final Map<Long, Integer> userPageMap = new HashMap<>();
    private final int totalPages = 2;

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        sendHelpPage(event, 1);
    }

    private void sendHelpPage(MessageReceivedEvent event, int page) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.MAGENTA);
        eb.setTitle("📜 Comandos do Bot — Página " + page + "/" + totalPages);
        eb.setTimestamp(Instant.now());
        eb.setFooter("Navegue com ⬅️ ➡️", event.getJDA().getSelfUser().getAvatarUrl());

        populateEmbedFields(eb, page);

        event.getChannel().sendMessageEmbeds(eb.build()).queue(message -> {
            message.addReaction(Emoji.fromUnicode("⬅️")).queue();
            message.addReaction(Emoji.fromUnicode("➡️")).queue();
            userPageMap.put(message.getIdLong(), page);
        });
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getUser() == null || event.getUser().isBot()) {
            return;
        }

        long messageId = event.getMessageIdLong();
        if (!userPageMap.containsKey(messageId)) {
            return;
        }

        int currentPage = userPageMap.get(messageId);
        String reactionName = event.getReaction().getEmoji().getName();

        if (reactionName.equals("⬅️") && currentPage > 1) {
            updateHelpPage(event, currentPage - 1);
            event.getReaction().removeReaction(event.getUser()).queue();
        }
        else if (reactionName.equals("➡️") && currentPage < totalPages) { // Corrected condition
            updateHelpPage(event, currentPage + 1);
            event.getReaction().removeReaction(event.getUser()).queue();
        }
    }


    private void updateHelpPage(@NotNull MessageReactionAddEvent event, int newPage) {
        event.getChannel().retrieveMessageById(event.getMessageId()).queue(message -> {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("📜 Comandos do Bot — Página " + newPage + "/" + totalPages);
            eb.setColor(Color.MAGENTA);
            eb.setTimestamp(Instant.now());
            eb.setFooter("Navegue com ⬅️ ➡️", event.getJDA().getSelfUser().getAvatarUrl());

            populateEmbedFields(eb, newPage);

            message.editMessageEmbeds(eb.build()).queue();
            userPageMap.put(message.getIdLong(), newPage);
        });
    }


    private void populateEmbedFields(EmbedBuilder eb, int page) {
        switch (page) {
            case 1 -> {
                eb.setDescription("🔧 Comandos básicos:");
                eb.addField("", "🏓 **!ping** — Responde com 'pong'.", true);
                eb.addField("", "🗣️ **!say** — Repete o que você digitar.", true);
                eb.addField("", "🧹 **!clear** — Apaga mensagens do chat.", true);
                eb.addField("", "👢 **!kick** — Expulsa um usuário.", true);
                eb.addField("", "⛔ **!ban** — Bane um usuário.", true);
                eb.addField("", "✅ **!unban** — Remove banimento.", true);
            }
            case 2 -> {
                eb.setDescription("⚙️ Comandos de Configuração e Informações:");
                eb.addField("", "📊 **!statsserver** — Estatísticas do servidor.", true);
                eb.addField("", "🧑 **!userinfo** — Info do usuário.", true);
                eb.addField("", "🎉 **!setchannelwelcome** — Canal de boas-vindas.", true);
                eb.addField("", "👋 **!setchannelleave** — Canal de despedida.", true);
                eb.addField("", "🔧 **!setautorole** — Cargo automático.", true);
                eb.addField("", "🔇 **!setmuterole** — Cargo de silenciado.", true);
                eb.addField("", "📰 **!news** — Últimas atualizações.", true);
            }
            // You can add more cases here for additional pages if needed
        }
    }
}
