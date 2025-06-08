package com.yuicottrill.discordbot.utils;

import com.yuicottrill.discordbot.models.entities.GuildData;
import com.yuicottrill.discordbot.service.GuildDataService;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberJoinOrLeaveListener extends ListenerAdapter {

    @Autowired
    private GuildDataService service;

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        GuildData data = service.getGuildReference(event.getGuild());

        TextChannel channel = event.getGuild().getTextChannelById(data.getWelcomeChannelId());
        Member member = event.getMember();
        String userName = event.getUser().getAsMention();


        Role role = event.getGuild().getRoleById(data.getAutoRoleId());

        member.getGuild().addRoleToMember(member, role ).queue();

        if (channel != null)
            channel.sendMessage("🎉 Olha quem acabou de pousar aqui: " + userName + "!Seja muito bem-vindo(a) ao servidor! 🚀 Sinta-se em casa e não esquece de pegar um café virtual ☕").queue();

    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event){

        GuildData data = service.getGuildReference(event.getGuild());

        TextChannel channel = event.getGuild().getTextChannelById(data.getLeaveMessageChannelId());
        String userName = event.getUser().getAsMention();

        if (channel != null)
            channel.sendMessage("😢 " + userName + " acabou de sair do servidor...Esperamos que volte algum dia. As estrelas ficam um pouco mais apagadas sem você ✨").queue();
    }
}
