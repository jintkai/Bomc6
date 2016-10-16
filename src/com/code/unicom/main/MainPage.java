package com.code.unicom.main;

import com.code.common.Page;
import com.code.unicom.main.page.LeftBodyPage;
import com.code.unicom.main.page.MenuPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

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
