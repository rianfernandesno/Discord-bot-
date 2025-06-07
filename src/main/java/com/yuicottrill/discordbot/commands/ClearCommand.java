package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ClearCommand implements Command{
    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        Integer amount = Integer.valueOf(args[0]);

        MessageChannel channel = event.getChannel();
        channel.getIterableHistory()
                .takeAsync(amount)
                .thenAccept(channel::purgeMessages);
    }
}
