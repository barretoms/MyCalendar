package br.com.divagar.calendar;

import java.time.LocalDateTime;

public class Episode extends Entry{

    LocalDateTime occurrence;
    Integer episodeId = null;
    String name;
    String description;

    public Episode(String name, LocalDateTime occurrence){
        super(occurrence, occurrence);
        this.occurrence = occurrence;
    }

    public LocalDateTime getOccurrence() {
        return occurrence;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public String getName() {
        return name;
    }

    public void setOccurrence(LocalDateTime occurrence) {
        this.occurrence = occurrence;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String descriiption){
        this.description = descriiption;
    }
    public String getDescription(){
        return this.description;
    }

    
}
