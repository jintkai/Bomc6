package com.code.unicom.main.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Administrator on 16-5-31.
 */
public class LeftBodyPage extends Page{
    public LeftBodyPage(EventFiringWebDriver webDriver)
    {
        super(webDriver);
    }
    @FindBy(how = How.ID,using = "up1")
    WebElement up1;

}
