package com.yuicottrill.discordbot.utils;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberJoinOrLeave extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){

        TextChannel channel = event.getGuild().getTextChannelById("1299132926201430088");
        String userName = event.getUser().getAsMention();

        channel.sendMessage("🎉 Olha quem acabou de pousar aqui: " + userName + "!\nSeja muito bem-vindo(a) ao servidor! 🚀 Sinta-se em casa e não esquece de pegar um café virtual ☕").queue();

    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event){

        TextChannel channel = event.getGuild().getTextChannelById("1299132926201430088");
        String userName = event.getUser().getAsMention();

        channel.sendMessage("😢 " + userName + " acabou de sair do servidor...\nEsperamos que volte algum dia. As estrelas ficam um pouco mais apagadas sem você ✨").queue();
    }
}
