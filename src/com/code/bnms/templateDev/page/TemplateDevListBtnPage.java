package com.code.bnms.templateDev.page;

import com.code.common.BtnPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Jin on 2014/8/21.
 */
public class TemplateDevListBtnPage extends BtnPage {
    public TemplateDevListBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(id = "btn-sync")
    WebElement syncBtn;
    @FindBy(id="btn-set")
    WebElement setBtn;
    public void sync()
    {
        tools.click(syncBtn);
        tools.alertAccept();
    }
    public void devSet(){
        tools.openModelDialog(setBtn);
    }

}
