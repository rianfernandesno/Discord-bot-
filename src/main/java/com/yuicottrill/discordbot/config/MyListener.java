package com.yuicottrill.discordbot.config;

import com.yuicottrill.discordbot.commands.CommandManager;
import com.yuicottrill.discordbot.utils.GeminiService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyListener  extends ListenerAdapter {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private CommandManager commandManager;

    public void onMessageReceived(MessageReceivedEvent event){
        String content = event.getMessage().getContentRaw();

        if (event.getAuthor().isBot()) return;

        //Command Linear
        commandManager.handle(event);

        //Mention
        if(event.getMessage().getMentions().getUsers().contains(event.getJDA().getSelfUser())) {
            // Só envia essa mensagem se NÃO for reply a uma mensagem do bot
            if (event.getMessage().getReferencedMessage() == null
                    || !event.getMessage().getReferencedMessage().getAuthor().isBot()) {
                event.getChannel().sendMessage("Utilize !help para verificar os comandos disponiveis ou der um replay nessa mensagem para conversar comigo!").queue();
            }
        }

        handleReplyToBot(event);

    }

    private void handleReplyToBot(MessageReceivedEvent event) {
        Message message = event.getMessage();

        if (message.getReferencedMessage() != null) {
            Message repliedTo = message.getReferencedMessage();

            if (repliedTo.getAuthor().isBot()) {
                String prompt = message.getContentRaw();

                var response = geminiService.generateContent(prompt);

                String aiResponse = "🤖 Não consegui gerar uma resposta.";

                if (response != null
                        && response.getCandidates() != null
                        && !response.getCandidates().isEmpty()) {

                    var content = response.getCandidates().get(0).getContent();
                    if (content != null
                            && content.getParts() != null
                            && !content.getParts().isEmpty()) {

                        aiResponse = content.getParts().get(0).getText();
                    }
                }

                event.getChannel().sendMessage(aiResponse).setMessageReference(message).queue();
            }
        }
    }

}

