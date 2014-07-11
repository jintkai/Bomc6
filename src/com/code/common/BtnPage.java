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
    @FindBy(how= How.ID,using="btn-edit")
    WebElement editBtn;
    @FindBy(how= How.ID,using=Data.btnDel)
    WebElement delBtn;
    @FindBy(how=How.ID,using=Data.btnSelect)
    WebElement selBtn;
    @FindBy(how=How.ID,using="btn-deploy")
    WebElement deploy;
    @FindBy(how=How.ID,using="btn-remove")
    WebElement remove;
    @FindBy(id="btn-startup")
    WebElement startup;
    @FindBy(id="btn-shutdown")
    WebElement shutdown;
    @FindBy(id = "btn-startDeploy")
    WebElement startDeploy;
    WebElement deploy_ok;
    @FindBy(id="btn-unload")
    WebElement unloadWK;
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
    public void remove()
    {
        tools.click(remove);
        tools.alertAccept();
        tools.click(deploy_ok);
    }
    public void unloadWK()
    {
        tools.click(unloadWK);
        tools.alertAccept();
        tools.click(deploy_ok);
    }
    public void deploy()
    {
        tools.click(deploy);
        tools.click(startDeploy);
        tools.click(deploy_ok);
    }

    public void shutdown()
    {
        tools.click(shutdown);
        tools.alertAccept();
        tools.click(deploy_ok);
    }
    public void startup()
    {
        tools.click(startup);
        tools.click(deploy_ok);
    }
}
