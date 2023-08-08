package br.com.divagar.task;

public class Objective {
    
    private int id;
    private String name;
    private String description;
    private int agendaId;


    public Objective () {}

    public Objective (int id, String name, String description, int agendaId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.agendaId = agendaId;

    }

    public int getId() {
        return id;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getAgendaId() {
        return agendaId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgendaId(int agendaId) {
        this.agendaId = agendaId;
    }

    public String toString () {
        return id+" | "+name+" | "+agendaId;
    }

    public static void main (String args[]) {
        test();
    }
    
    public static void test() {
        Objective obj = new Objective(1, "teste", 1);
        System.out.println(obj);

            }
}
