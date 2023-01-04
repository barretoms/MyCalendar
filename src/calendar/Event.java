package calendar;

import java.time.LocalDateTime;

public class Event extends Entry {

    private Integer eventId = null;
    private String description = "Blank for testing";
    private String name;

    public Event(String name, LocalDateTime start, LocalDateTime finish){
        super(start, finish);
        super.setType("Event");
        this.name = name;
    }

    public Event(String name, LocalDateTime dtStart, LocalDateTime dtFinish, LocalDateTime dtCreation, LocalDateTime dtModification, String description, Integer entryId, Integer eventId){
        super(entryId, dtStart, dtFinish, dtModification, dtCreation, "Event");
        this.name = name;
        this.eventId = eventId;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Integer getEventId() {
        return eventId;
    }

    @Override
    public String toString(){
        return "\n"+super.toString()+"\nevent_id: "+eventId+
        "\nname: "+ name+
        "\ndescription: "+description+"\n------------";
    }

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = LocalDateTime.now();
        Entry entry = new Event("abc",start,finish);
        System.out.println(entry.getClass().getSimpleName()
        );
    }
    
}
