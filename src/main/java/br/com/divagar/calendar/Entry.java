package br.com.divagar.calendar;

import java.time.LocalDateTime;

public abstract class Entry {
    
    private Integer entryId = null; // if id == null => entry not in database/xml
    private LocalDateTime modificationLocalDateTime;
    private LocalDateTime creationLocalDateTime;
    private LocalDateTime starLocalDateTime;
    private LocalDateTime finishLocalDateTime;
    private String type;

    public Entry(LocalDateTime dtStart, LocalDateTime dtFinish){
        this.creationLocalDateTime = LocalDateTime.now();
        this.modificationLocalDateTime = this.creationLocalDateTime;
        this.starLocalDateTime = dtStart;
        this.finishLocalDateTime = dtFinish;
    }

    public Entry(Integer entryId, LocalDateTime dtStart, LocalDateTime dtFinish, LocalDateTime dtModification, LocalDateTime dtCreation, String type){
        this.entryId = entryId;
        this.creationLocalDateTime = dtCreation;
        this.modificationLocalDateTime = dtModification;
        this.starLocalDateTime = dtStart;
        this.finishLocalDateTime = dtFinish;
        this.type = type;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public void setModificationLocalDateTime(LocalDateTime modificationLocalDateTime) {
        this.modificationLocalDateTime = modificationLocalDateTime;
    }

    public void setCreationLocalDateTime(LocalDateTime creationLocalDateTime) {
        this.creationLocalDateTime = creationLocalDateTime;
    }

    public void setStarLocalDateTime(LocalDateTime starLocalDateTime) {
        this.starLocalDateTime = starLocalDateTime;
    }

    public void setFinishLocalDateTime(LocalDateTime finishLocalDateTime) {
        this.finishLocalDateTime = finishLocalDateTime;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public LocalDateTime getModificationLocalDateTime() {
        return modificationLocalDateTime;
    }

    public LocalDateTime getCreationLocalDateTime() {
        return creationLocalDateTime;
    }

    public LocalDateTime getStarLocalDateTime() {
        return starLocalDateTime;
    }

    public LocalDateTime getFinishLocalDateTime() {
        return finishLocalDateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        
        return "entry_id: "+entryId+
        "\nCreated -> Modified: "+creationLocalDateTime+"->"+modificationLocalDateTime+
        "\nEntry Start |-> Entry Finish: "+starLocalDateTime+" |-> "+ finishLocalDateTime;
    }

}
