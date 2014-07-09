package com.code.common;

import com.code.page.kbplist.page.KbpFormPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 06/07/2014.
 */
public class BtnPage extends Page {
    @FindBy(how= How.ID,using=Data.btnAdd)
    WebElement addBtn;
    @FindBy(how= How.ID,using=Data.btnEdit)
    WebElement editBtn;
    @FindBy(how= How.ID,using=Data.btnDel)
    WebElement delBtn;
    @FindBy(how=How.ID,using=Data.btnSelect)
    WebElement selBtn;
    public FormPage openFormByAdd()
    {
        tools.click(addBtn);
        return new FormPage();
    }
    public  void  deleteForm()
    {
        tools.click(delBtn);
        tools.alertAccept();
    } public  FormPage  editForm()
    {
        tools.click(editBtn);
        return new FormPage();
    }
    public void select()
    {
        tools.click(selBtn);
    }
}
