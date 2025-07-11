package com.yuicottrill.discordbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommandManager {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandManager(
            SetWelcomeChannel setWelcomeChannel,
            SetLeaveMessageChannel setLeaveMessageChannel,
            SetAutoRole setAutoRole,
            SetMuteRole setMuteRole,
            UpdatesCommand updatesCommand,
            HelpCommand helpCommand
    ){
        commands.put("ping", new PingCommand());
        commands.put("say", new SayCommand());
        commands.put("help", helpCommand);
        commands.put("clear", new ClearCommand());
        commands.put("kick", new KickCommand());
        commands.put("ban", new BanCommand());
        commands.put("unban", new UnbanCommand());
        commands.put("mute", new MuteCommand());
        commands.put("unmute", new UnmutedCommand());
        commands.put("statsserver", new StatsCommand());
        commands.put("userinfo", new UserInfoCommand());
        commands.put("setchannelwelcome", setWelcomeChannel);
        commands.put("setchannelleave", setLeaveMessageChannel);
        commands.put("setautorole", setAutoRole);
        commands.put("setmuterole", setMuteRole);
        commands.put("news", updatesCommand);
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
