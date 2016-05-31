package com.code.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jin on 2014/9/15.
 */
public  class Config {
    public  Properties prop=new Properties();
    public  FileInputStream in;
    public Config() {
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
}
