package calendar;

import java.time.LocalDateTime;

public abstract class Entry {
    
    private Integer id = null; // if id == null => entry not in database/xml
    private LocalDateTime modificationLocalDateTime;
    private LocalDateTime creationLocalDateTime;
    private LocalDateTime starLocalDateTime;
    private LocalDateTime finishLocalDateTime;
    private String name;
    private String type;

    public Entry(String name, LocalDateTime dtStart, LocalDateTime dtFinish){
        this.name = name;
        this.creationLocalDateTime = LocalDateTime.now();
        this.modificationLocalDateTime = this.creationLocalDateTime;
        this.starLocalDateTime = dtStart;
        this.finishLocalDateTime = dtFinish;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getId() {
        return id;
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

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return "Entry";
    }

}
