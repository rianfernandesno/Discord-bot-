package com.yuicottrill.discordbot.config;

import com.yuicottrill.discordbot.commands.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class MyListener  extends ListenerAdapter {

    private final CommandManager commandManager = new CommandManager();

    public void onMessageReceived(MessageReceivedEvent event){
        String content = event.getMessage().getContentRaw();

        if (event.getAuthor().isBot()) return;

        commandManager.handle(event);

        if(event.getMessage().getMentions().getUsers().contains(event.getJDA().getSelfUser())){
            event.getChannel().sendMessage("Utilize !help para verificar os comandos disponiveis!").queue();
        }
    }
}
