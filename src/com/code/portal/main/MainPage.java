package com.code.portal.main;

import com.code.common.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by Administrator on 16-5-31.
 */
public class MainPage extends Page{
    public MainPage(EventFiringWebDriver eventFiringWebDriver){
        super(eventFiringWebDriver);
    }
    public PortalHeader portalHeader=new PortalHeader(eventDriver);

    public WebElement switchWindow(String str){
        WebElement frame=null;
        List<WebElement> iframes=tools.findElements(eventDriver, By.tagName("iframe"));
        for (WebElement e:iframes){
            String src=e.getAttribute("src");
            System.out.println(src);
            if(src.contains(str)){
                return e;
            }
        }
        return frame;
    }
    public void selectItem(String str){
        portalHeader.selectItem(str);
    }
    public void logout(){
        tools.switchToFrame();
        portalHeader.logout();
    }
}
