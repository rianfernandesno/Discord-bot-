package com.yuicottrill.discordbot.repositories;

import com.yuicottrill.discordbot.models.entities.Changelog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangelogRepository extends MongoRepository<Changelog, String> {

    Changelog findByVersion(String version);
}
