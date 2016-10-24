package com.code.bnms.agentList.page;

import com.code.common.BtnPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgBtnPage extends BtnPage {
    public AgBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @Override
    public void delete()
    {
        tools.click(delBtn);
        tools.alertAccept();
        tools.alertAccept();
    }
    @FindBy(id="btn-busiConfig")
    WebElement btn_busiConfig;
    public void busiConfig(){
        tools.click(btn_busiConfig);
    }
}
