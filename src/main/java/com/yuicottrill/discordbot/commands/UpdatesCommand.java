package com.yuicottrill.discordbot.commands;

import com.yuicottrill.discordbot.models.entities.Changelog;
import com.yuicottrill.discordbot.service.ChangelogService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatesCommand  implements Command{
    @Autowired
    private ChangelogService service;

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder eb = new EmbedBuilder();
        Changelog latest = service.findByVersion("1.0");

        if (latest == null) {
            event.getChannel().sendMessage("❌ Nenhuma versão encontrada.").queue();
            return;
        }

        eb.setTitle("📌 Atualização - Versão " + latest.getVersion());
        eb.setDescription("Confira abaixo as novidades desta versão!");
        eb.setColor(0x00BFFF); // Azul claro

        for (String change : latest.getChanges()) {
            eb.addField("", "➤ " + change, true);
        }

        eb.setFooter("Data de lançamento: " + latest.getCreated().toString());

        event.getChannel().sendMessageEmbeds(eb.build()).queue();
    }

}
