package com.code.page.ibnmsConfig.alarmTemplate.page;

import com.code.common.BtnPage;
import com.code.common.FormPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Jin on 2014/8/4.
 */
public class AlarmTemplateBtnPage extends BtnPage{
    public AlarmTemplateBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(id="btn-add")
    WebElement addBtn;
    @FindBy(id="btn-edit")
    WebElement editBtn;
    @FindBy(id="btn-del")
    WebElement delBtn;
    @FindBy(id = "btn-save")
    WebElement saveBtn;
    public com.code.common.FormPage add()
    {
        tools.click(addBtn);
        return new FormPage(eventDriver);
    }
    public void delte()
    {
        tools.click(delBtn);
        tools.alertAccept();
    }
    public void save()
    {
        tools.click(saveBtn);
        tools.alertAccept();
    }
}
