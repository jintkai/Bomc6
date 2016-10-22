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
    public void selectFrame2(WebDriver driver, Object ... framePaths){

        if (framePaths.length!=1){
            for (int i=0;i<framePaths.length;i++){
                if(framePaths[i] instanceof WebElement){
                    driver.switchTo().frame((WebElement)framePaths[i]);
                }
                else if(framePaths[i]instanceof String){
                    driver.switchTo().frame((String)framePaths[i]);
                }else{
                    driver.switchTo().frame((Integer) framePaths[i]);
                }
            }

        }else{
            if(framePaths[0] instanceof WebElement){
                driver.switchTo().frame((WebElement)framePaths[0]);
            }
            else if(framePaths[0]instanceof String){
                driver.switchTo().frame((String)framePaths[0]);
            }else{
                driver.switchTo().frame((Integer) framePaths[0]);
            }
        }
    }
}
