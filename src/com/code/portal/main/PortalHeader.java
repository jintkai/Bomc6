package com.code.portal.main;

import com.code.common.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by jon on 2016/10/13.
 */
public class PortalHeader extends Page {
    public PortalHeader (EventFiringWebDriver eventFiringWebDriver){
        super(eventFiringWebDriver);
    }
    @FindBy(id="ptl-subsystem-list")
    WebElement subSystemList;
    //@FindBy(className = "item ng-binding ng-scope")
    List<WebElement> menuItems;
    @FindBy(id="ptl-user-bar")
    WebElement userBar;
    public void printMenuItems(){
        menuItems=tools.findElements(eventDriver, By.xpath("//div[contains(@class , 'item ng-binding ng-scope')]"));
        for(WebElement item:menuItems){

            System.out.println(item.getText());
        }
    }
    public void selectItem(String str){
        menuItems=tools.findElements(eventDriver, By.xpath("//div[contains(@class , 'item ng-binding ng-scope')]"));
        for(WebElement item:menuItems){

            if(item.getText().equals(str)){
                tools.click(item);
                break;
            }
        }
    }
    public void logout(){
        List<WebElement> elements=tools.findElements(userBar,By.tagName("a"));
        for(WebElement e:elements){
            if(tools.getAttribute(e,"title").equals("安全退出")){
                tools.click(e);
                tools.alertAccept();
                return;
            }
        }
    }
}
