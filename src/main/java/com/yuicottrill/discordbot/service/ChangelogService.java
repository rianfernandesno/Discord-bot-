package com.yuicottrill.discordbot.service;

import com.yuicottrill.discordbot.models.entities.Changelog;
import com.yuicottrill.discordbot.repositories.ChangelogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ChangelogService {

    @Autowired
    private ChangelogRepository repository;


    public void saveChangelog(String version, List<String> changes){
        if (version == null || version.isBlank() || changes == null)
            throw new IllegalArgumentException("Versão e mudanças são obrigatórios");


        Changelog newLog = new Changelog();
        newLog.setCreated(Instant.now());
        newLog.setVersion(version);
        newLog.getChanges().addAll(changes);


        repository.save(newLog);
    }

    public Changelog findByVersion(String version){

        return repository.findByVersion(version);
    }

}
