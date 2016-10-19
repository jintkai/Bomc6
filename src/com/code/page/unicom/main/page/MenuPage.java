package com.code.page.unicom.main.page;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by Administrator on 16-6-1.
 */
public class MenuPage extends Page{
    public MenuPage(EventFiringWebDriver eventFiringWebDriver){
        super(eventFiringWebDriver);
    }
    @FindBy(how= How.ID,using = "menu-list")
    WebElement menuList;
    public void selectMenu(String MainMenu,String subMenu){

        List<WebElement> elements=tools.findElements((SearchContext) menuList, By.tagName("li"));
        for(WebElement element : elements){
            WebElement element_h4=element.findElement(By.tagName("h4"));
            if(element_h4.getText().equals(MainMenu)) {
                tools.click(element);
                List<WebElement> subMenuList=element.findElements(By.tagName("a"));
                for (WebElement submenu : subMenuList){
                    if (submenu.getText().equals(subMenu))
                    {
                        tools.click(submenu);
                        return;
                    }
                }
                return;
            }
        }
    };
}
