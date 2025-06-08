package com.yuicottrill.discordbot.service;

import com.yuicottrill.discordbot.models.entities.GuildData;
import com.yuicottrill.discordbot.repositories.GuildDataRepository;
import net.dv8tion.jda.api.entities.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class GuildDataService {

    @Autowired
    private GuildDataRepository repository;

    public void saveGuild(Guild guild) {
        GuildData guildData = new GuildData(
                guild.getId(),
                guild.getName(),
                guild.getOwnerId(),
                guild.getMemberCount(),
                Instant.now(),
                guild.getTimeCreated().toInstant(),
                guild.getIconUrl(),
                null,
                null,
                null,
                null
        );

        if (!repository.existsById(guildData.getId())) {
            repository.save(guildData);
        }
    }

    public void deleteGuildById(String guildId) {
        repository.deleteById(guildId);
    }

    public GuildData getGuildReference(Guild guild){
        String guildId = guild.getId();


        return repository.findById(guildId).orElseThrow(()-> new IllegalStateException("Não encontrado"));
    }

    public void saveWelcome(Guild guild, String welcomeId){
        GuildData data = repository.findById(guild.getId()).orElseThrow();
        data.setWelcomeChannelId(welcomeId);
        repository.save(data);
    }

    public void saveLeaveChannel(Guild guild, String leaveId){
        GuildData data = repository.findById(guild.getId()).orElseThrow();
        data.setLeaveMessageChannelId(leaveId);
        repository.save(data);
    }

    public void saveAutoRole(Guild guild, String autoRoleId){
        GuildData data = repository.findById(guild.getId()).orElseThrow();
        data.setAutoRoleId(autoRoleId);
        repository.save(data);
    }

    public void saveMutedRole(Guild guild, String muteRoleId){
        GuildData data = repository.findById(guild.getId()).orElseThrow();
        data.setMutedRoleId(muteRoleId);
        repository.save(data);
    }

}
