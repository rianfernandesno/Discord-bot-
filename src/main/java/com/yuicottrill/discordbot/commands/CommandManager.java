package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandManager(){
        commands.put("ping", new PingCommand());
        commands.put("say", new SayCommand());
        commands.put("help", new HelpCommand());
        commands.put("clear", new ClearCommand());

    }

    public void handle(MessageReceivedEvent event){
        String[] split = event.getMessage().getContentRaw().split(" ");
        String commandName = split[0].substring(1);
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        Command command = commands.get(commandName);
        if(command != null){
            command.execute(event, args);
        }
    }
}
