package calendar;

import java.time.LocalDateTime;

public class Event extends Entry {

    private LocalDateTime start;
    private LocalDateTime finish;
    private String description;


    public Event(String name, LocalDateTime start, LocalDateTime finish){
        super(name);
        this.start = start;
        this.finish = finish;
    }  
    public void setStart(LocalDateTime start){
        this.start = start;
    }
    public LocalDateTime getStart(){
        return this.start;
    }
    public void setFinish(LocalDateTime finish){
        this.finish = finish;
    }
    public LocalDateTime getFinish(){
        return this.finish;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }


}
