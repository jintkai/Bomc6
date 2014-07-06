package com.code.page.kbplist.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpBtnPage extends Page {

    @FindBy(how= How.ID,using="btn-add")
    WebElement addBtn;
    @FindBy(how= How.ID,using="btn-edit")
    WebElement editBtn;
    @FindBy(how= How.ID,using="btn-del")
    WebElement delBtn;
    public KbpFormPage openFormByAdd()
    {
        tools.click(addBtn);
        return new KbpFormPage();
    }
    public  void  deleteForm()
    {
        tools.click(delBtn);
        tools.alertAccept();
    } public  KbpFormPage  editForm()
    {
        tools.click(editBtn);
        return new KbpFormPage();
    }
}
