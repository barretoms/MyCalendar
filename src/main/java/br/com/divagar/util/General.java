package br.com.divagar.util;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class General {

    public static void main(String[] args){

        test();
        testLoadProperties();
    }
    
    public static Properties loadProperties (String filePath) throws IOException{
        General general = new General();
        InputStream proprietiesInputStream = general.getFileAsIOStream(filePath);
        Properties properties = new Properties();
        properties.load(proprietiesInputStream);
        return properties;
    }

    private InputStream getFileAsIOStream(final String fileName) 
    {
        InputStream ioStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(fileName);
        
        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    public static String getRandomAlphaNumericString(Integer length){

        String randString = new String();

            String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
            String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String numbers = "0123456789";

            String characters = numbers + lowerCaseLetters + upperCaseLetters;

            Random rand = new Random();

            for(int i = 0; i < length; i++){
                randString += characters.charAt(rand.nextInt(characters.length()));
            }

        return randString;
    }

    public static String getRandomCustomString(Integer length, String charString){

        String randString = new String();

            Random rand = new Random();

            for(int i = 0; i < length; i++){
                randString += charString.charAt(rand.nextInt(charString.length()));
            }

        return randString;
    }

    public static String getSha256FromString (String text) {
        String result = "Encripted String";
        // TODO Function to encript a text to sha256
        return result;
    }

    public static void test(){
        System.out.println(getSha256FromString("Exemple test"));
    }
    
    public static LocalDateTime stringToDateTime(String dt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dt, formatter);
        return ldt;
    }

    public static String dateTimeToString(LocalDateTime ldt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ldtString = ldt.format(formatter);
        return ldtString;
    }
    public static void testLoadProperties(){
        try {
            Properties prop = loadProperties("config.properties");
            System.out.println(prop.getProperty("connectionType"));
        System.out.println(prop.getProperty("dbPassword"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
