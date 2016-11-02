package com.code.common;



import java.io.*;
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
            //prop.load(new InputStreamReader(Client.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8"));
            //prop.load(in,"UTF-8");
            InputStream inputStream = new FileInputStream("./config/config.properties");
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            prop.load(bf);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return prop.getProperty(key);
    }

}
