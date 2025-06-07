package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SayCommand implements Command{


    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String messages = String.join(" ", args);
        event.getChannel().sendMessage(messages).queue();
    }
}
