package com.yuicottrill.discordbot.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "changelogs")
public class Changelog {

    @Id
    private String id;
    private String version;
    private Instant created;
    private List<String> changes = new ArrayList<>();

    public Changelog(){}

    public Changelog(String id, String version, Instant created) {
        this.id = id;
        this.version = version;
        this.created = created;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public List<String> getChanges() {
        return changes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Changelog changelog = (Changelog) o;
        return Objects.equals(id, changelog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
