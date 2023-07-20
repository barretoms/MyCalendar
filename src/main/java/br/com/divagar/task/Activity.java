package br.com.divagar.task;

public class Activity {
    
    int id;
    String name;
    String description;
    float alignment;
    float pleasantness;
    float usefulness;
    int objectiveId;
    int agendaId;

    public Activity (){}

    public Activity(int id, String name, String description, float alignment, float pleasantness, float usefulness,
            int objectiveId, int agendaId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alignment = alignment;
        this.pleasantness = pleasantness;
        this.usefulness = usefulness;
        this.objectiveId = objectiveId;
        this.agendaId = agendaId;
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

    public float getAlignment() {
        return alignment;
    }

    public void setAlignment(float alignment) {
        this.alignment = alignment;
    }

    public float getPleasantness() {
        return pleasantness;
    }

    public void setPleasantness(float pleasantness) {
        this.pleasantness = pleasantness;
    }

    public float getUsefulness() {
        return usefulness;
    }

    public void setUsefulness(float usefulness) {
        this.usefulness = usefulness;
    }

    public int getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(int objectiveId) {
        this.objectiveId = objectiveId;
    }

    public int getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(int agendaId) {
        this.agendaId = agendaId;
    }

    
    
}
