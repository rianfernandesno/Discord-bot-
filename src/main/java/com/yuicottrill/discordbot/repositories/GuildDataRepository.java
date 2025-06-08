package com.yuicottrill.discordbot.repositories;

import com.yuicottrill.discordbot.models.entities.GuildData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuildDataRepository extends MongoRepository<GuildData, String> {
}
