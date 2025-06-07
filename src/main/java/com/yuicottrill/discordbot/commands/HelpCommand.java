package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand implements Command{

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String[] commandsList = {"!ping", "!say"};
        event.getChannel().sendMessage(String.join(" ", commandsList)).queue();
    }
}
