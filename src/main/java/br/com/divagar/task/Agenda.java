package br.com.divagar.task;

public class Agenda {

    int id;
    String name;
    String description;
    
    public Agenda(){}

    public Agenda( int id, String name, String description){

        this.id = id;
        this.name = name;
        this.description = description;

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

}
