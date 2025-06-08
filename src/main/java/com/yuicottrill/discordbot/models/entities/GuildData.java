package com.yuicottrill.discordbot.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "guilds")
public class GuildData {

    @Id
    private String id; // ID do servidor (guild) no Discord
    private String name; // Nome do servidor
    private String ownerId; // ID do dono do servidor
    private int memberCount; // Quantidade atual de membros
    private Instant joinedAt; // Data em que o bot entrou no servidor
    private Instant createdAt; // Data de criação do servidor
    private String iconUrl; // URL do ícone do servidor

    private String welcomeChannelId; // ID do canal de boas-vindas
    private String leaveMessageChannelId; // ID do canal de mensagem de saída
    private String mutedRoleId; // ID da role de mutado
    private String autoRoleId; // ID da role que será atribuída ao entrar

    public GuildData(){}

    public GuildData(String id, String name, String ownerId, int memberCount, Instant joinedAt, Instant createdAt, String iconUrl, String welcomeChannelId, String leaveMessageChannelId, String mutedRoleId, String autoRoleId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.memberCount = memberCount;
        this.joinedAt = joinedAt;
        this.createdAt = createdAt;
        this.iconUrl = iconUrl;
        this.welcomeChannelId = welcomeChannelId;
        this.leaveMessageChannelId = leaveMessageChannelId;
        this.mutedRoleId = mutedRoleId;
        this.autoRoleId = autoRoleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWelcomeChannelId() {
        return welcomeChannelId;
    }

    public void setWelcomeChannelId(String welcomeChannelId) {
        this.welcomeChannelId = welcomeChannelId;
    }

    public String getLeaveMessageChannelId() {
        return leaveMessageChannelId;
    }

    public void setLeaveMessageChannelId(String leaveMessageChannelId) {
        this.leaveMessageChannelId = leaveMessageChannelId;
    }

    public String getMutedRoleId() {
        return mutedRoleId;
    }

    public void setMutedRoleId(String mutedRoleId) {
        this.mutedRoleId = mutedRoleId;
    }

    public String getAutoRoleId() {
        return autoRoleId;
    }

    public void setAutoRoleId(String autoRoleId) {
        this.autoRoleId = autoRoleId;
    }
}
