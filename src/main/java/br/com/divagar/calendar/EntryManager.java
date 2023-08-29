package br.com.divagar.calendar;

import java.time.LocalDateTime;

public interface EntryManager {
    

    public void includeEvent(Event event) throws Exception;
    public abstract Entry getEntries (LocalDateTime date);

    // TODO include those

    // public abstract void modifyEntry (Entry entry);

}
