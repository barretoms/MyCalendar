package login;

import java.sql.Connection;
import java.io.*;
import org.w3c.dom.*;
import util.Xml;


public class DbManager {

    private String filePath = "resources\\dbinfo.xml";

    public DbManager(){

    }

    public static void main(String[] args){
        test();
    }

    public void registerDB (String name, String url, String username, String password){

        //TODO insert DB credentials into config file 
    }

    public static Connection getConnection (String entry){
        Connection conn = null;

            // TODO implement a variable to get a connection based on the name on the config file

        return conn;
    }

    public void connect(){

        // TODO implement function to handle connection error
    }

    public Boolean isCompliant(){

        // TODO implement function 

        return true;
    }

    public Boolean isValidEntry(){

        // TODO verifies if entry is in the config file

        return false;
    }

    public Document loadDbInfo() throws Exception{

        File file = new File(filePath);

        if(!file.exists()){
            System.out.println("Creating file dbinfo.xml...");
            Document document = Xml.createBlankDocument();
            Element root = document.createElement("root");
            document.appendChild(root);
            FileOutputStream output = new FileOutputStream(file);
            Xml.writeXml(document, output);
            System.out.print(Xml.prettyPrintByTransformer(document, 2, false));
            return document;
        }
        System.out.println("Loading file dbinfo.xml...");
        Document document = Xml.readXmlFile(file);
        System.out.print(Xml.prettyPrintByTransformer(document, 2, false));
        return document;

    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public static void test (){
        DbManager dm = new DbManager();
        try {
            dm.loadDbInfo();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

}
