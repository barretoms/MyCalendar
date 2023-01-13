package br.com.divagar.login;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.com.divagar.util.Xml;

public class ManagedDb {

    private String name = "";
    private String connector = "jdbc:mysql";
    private String address = "";
    private String username = "";
    private String password = "";
    
    public ManagedDb(Element element){
        
        String name = element.getAttribute("name");
        System.out.println(name);
        this.connector = element.getElementsByTagName("connector").item(0).getTextContent();
        this.address = element.getElementsByTagName("address").item(0).getTextContent();
        this.username = element.getElementsByTagName("username").item(0).getTextContent();
        this.password = element.getElementsByTagName("password").item(0).getTextContent();       
        
    }

    public ManagedDb(String name, String connector, String address, String username, String password){
        this.name = name;
        this.connector = connector;
        this.address = address;
        this. username = username;
        this.password = password;
    }

    public ManagedDb(){}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getConnector(){
        return connector;
    }

    public void setConnector(String connector){
        this.connector = connector;
    }

    public String connectorString(){
        return connector+":"+address;
    }
    
    public String toString(){

        System.out.println(name+connector+address+username+password); // FIXME Comment
        return name + " -> " + this.connectorString(); // Uncoment
    }

    public Element getElement() throws Exception{
        Document document = Xml.createBlankDocument();
        Element database = document.createElement("database");
        database.setAttribute("name", this.name);

        Element connector = document.createElement("connector");
        connector.setNodeValue(this.connector);
        database.appendChild(connector);

        Element address = document.createElement("address");
        address.setNodeValue(this.address);
        database.appendChild(address);

        Element username = document.createElement("username");
        username.setNodeValue(this.username);
        database.appendChild(username);

        Element password = document.createElement("password");
        password.setNodeValue(this.password);
        database.appendChild(password);

        return database;
    }

    public static void main(String[] args){
        test();
    }

    public static void test() {
        ManagedDb mdb = new ManagedDb();
        try {
            Element database = mdb.getElement();
            System.out.println(database.getFirstChild().getTextContent());  
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Xml.validateAgainsXsd("resources\\dbinfo.xml", "resources\\dbinfo.xsd")) System.out.println("Xml is valid!");

    }
    
}
