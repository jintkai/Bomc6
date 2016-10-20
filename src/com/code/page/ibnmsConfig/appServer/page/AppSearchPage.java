package com.code.page.ibnmsConfig.appServer.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Jin on 2014/8/10.
 */
public class AppSearchPage extends Page {
    @FindBy(id="applyModule")
    public WebElement applyModule;
    public AppSearchPage(EventFiringWebDriver eventDriver){
        super(eventDriver);
    }
}
