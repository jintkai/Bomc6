package com.code.page.unicom.main;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Administrator on 16-5-31.
 */
public class MainPage extends Page{
    public MainPage(EventFiringWebDriver eventFiringWebDriver){
        super(eventFiringWebDriver);
    }
    @FindBy(how= How.ID,using = "menu-list")
    WebElement menuList;
    public void selectMenu(String MainMenu,String subMenu){

        List<WebElement> elements=tools.findElements((SearchContext) menuList, By.tagName("li"));
        for(WebElement element : elements){
            WebElement element_h4=element.findElement(By.tagName("h4"));
            System.out.println("h4:"+element_h4.getText());
            if(element_h4.getText().equals(MainMenu)) {

                List<WebElement> subMenuList=element.findElements(By.tagName("a"));
                for (WebElement submenu : subMenuList){
                    System.out.println("a:"+submenu.getText()+":"+submenu.getAttribute(""));
                }
                return;
            }
        }
        System.out.println("return");
    };
}
