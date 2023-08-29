package br.com.divagar.task;

import java.time.LocalDateTime;

public class Agenda {
    // * If id = -1 then agenda is not on the database
    // * if id = -2 then agenda is not up to date
    int id;
    String name;
    String description;
    LocalDateTime timestamp = null;
    
    public Agenda(){}

    public Agenda( int id, String name, String description, LocalDateTime timestamp){

        this.id = id;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;

    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        String rs = id + " | " + name + " | " + description;
        return rs;
    }
    public static void main (String[] args){

    }

    public static void test(){
        
    }

}
