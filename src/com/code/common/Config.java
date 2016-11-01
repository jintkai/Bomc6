package com.code.common;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jin on 2014/9/15.
 */
public  class Config {
    public static Properties prop=new Properties();
    static {
        FileInputStream in=null;
        try {
             in=new FileInputStream("./config/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return prop.getProperty(key);
    }

}
