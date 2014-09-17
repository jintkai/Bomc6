package com.code.page.ibnmsConfig.alarmConfUpdate.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Jin on 2014/7/26.
 */
public class UpdateBtnPage extends Page {
    public UpdateBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(id="btn-update")
    WebElement updateBtn;
    @FindBy(id="btn-start-task")
    WebElement startTaskBtn;
    @FindBy(id="btn-del")
    WebElement delBtn;

    public void updata()
    {
        tools.click(updateBtn);
    }
    public void startTask()
    {
        tools.click(startTaskBtn);
        tools.alertAccept();
    }
    public void delete()
    {
        tools.click(delBtn);
        tools.alertAccept();
    }
}
