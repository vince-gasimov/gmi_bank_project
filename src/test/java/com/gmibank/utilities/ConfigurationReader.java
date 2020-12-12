package com.gmibank.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationReader {
    static Properties properties;
    static String path = "configuration.properties";
    static{

        try{
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();
            properties.load(file);
        }catch (Exception e){

        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }


    public static void changePropertyValue(String key, String newValue){
        properties.setProperty(key, newValue);
        try{
            properties.store(new FileOutputStream(path), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
