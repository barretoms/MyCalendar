package calendar;

import java.time.LocalDateTime;

public class Event extends Entry {

    private String description;

    public Event(String name, LocalDateTime start, LocalDateTime finish){
        super(name, start, finish);
        super.setType("Event");
    }
      
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = LocalDateTime.now();
        Entry entry = new Event("abc",start,finish);
        System.out.println(entry.getClass().getSimpleName()
        );
    }

}
