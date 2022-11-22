package calendar;

import java.time.LocalDateTime;

public class Episode extends Entry{

    LocalDateTime occurrence;
    String description;

    public Episode(String name, LocalDateTime occurrence){
        super(name, occurrence, occurrence);
        this.occurrence = occurrence;
    }

    public void setDescription(String descriiption){
        this.description = descriiption;
    }
    public String getDescription(){
        return this.description;
    }

    
}
