package com.code.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 2016/10/17.
 * 实现Frame跳转
 */
public class FrameHelper {
    private WebDriver driver;
    private String frameID;
    private List<String> framePath=new ArrayList<String>();
    public FrameHelper(WebDriver driver,String frameID){
        this.driver=driver;
        this.frameID=frameID;
    }
    public FrameHelper(WebDriver driver,List<String> framePath){
        this.driver=driver;
        this.framePath=framePath;
    }
    public void selectFrame(){
        if (framePath.size()>0){
            for(int i=0;i<framePath.size();i++){
                driver.switchTo().frame(framePath.get(i));
            }
        }
        if(frameID!=null){
            driver.switchTo().frame(frameID);
        }
    }

    public void deSelectFrame(){
        driver.switchTo().defaultContent();
    }
}
