package calendar;

import java.time.LocalDateTime;

public interface EntryManager {
    

    
    public abstract Entry getEvents (LocalDateTime date);
    public abstract boolean intersects(LocalDateTime start, LocalDateTime finish);
    public abstract void includeEntry (Entry entry);

    // TODO include those

    // public abstract void modifyEntry (Entry entry);

}
