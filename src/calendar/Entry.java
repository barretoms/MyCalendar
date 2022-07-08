package calendar;

public class Entry {
    
    private String name;

    public Entry(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return "Entry";
    }

}
