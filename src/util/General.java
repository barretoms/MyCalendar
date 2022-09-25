package util;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class General {

    public static void main(String[] args){

        test();
    }
    
    public static Properties loadProperties (String filePath) throws IOException{
        FileInputStream proprietiesInputStream = new FileInputStream(filePath);
        Properties properties = new Properties();
        properties.load(proprietiesInputStream);
        return properties;
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

    public static void test(){
        
    }
    
}
