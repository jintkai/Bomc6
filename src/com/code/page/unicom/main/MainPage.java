package com.code.page.unicom.main;

import com.code.common.Page;
import com.code.page.unicom.main.page.LeftBodyPage;
import com.code.page.unicom.main.page.MenuPage;
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
    public MenuPage menuPage=new MenuPage(eventDriver);
    public LeftBodyPage leftBodyPage=new LeftBodyPage(eventDriver);

}
