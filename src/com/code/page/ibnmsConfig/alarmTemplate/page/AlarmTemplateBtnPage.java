package com.code.page.ibnmsConfig.alarmTemplate.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jin on 2014/8/4.
 */
public class AlarmTemplateBtnPage extends Page{
    @FindBy(id="btn-add")
    WebElement addBtn;
    @FindBy(id="btn-edit")
    WebElement editBtn;
    @FindBy(id="btn-del")
    WebElement delBtn;
    public void add()
    {
        tools.click(addBtn);
    }
    public void delte()
    {
        tools.click(delBtn);
        tools.alertAccept();
    }
}
