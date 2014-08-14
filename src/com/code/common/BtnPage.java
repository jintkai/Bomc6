package com.code.common;

import com.code.page.ibnmsConfig.kbplist.page.KbpFormPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 06/07/2014.
 * 包含一些通用的按钮，增删、部署卸载等按钮
 */
public class BtnPage extends Page {
    @FindBy(how= How.ID,using=Data.btnAdd)
    public WebElement addBtn;
    @FindBy(how= How.ID,using="btn-edit")
    public WebElement editBtn;
    @FindBy(how= How.ID,using=Data.btnDel)
    public WebElement delBtn;
    @FindBy(how=How.ID,using=Data.btnSelect)
    public WebElement selBtn;
    @FindBy(how=How.ID,using="btn-deploy")
    public WebElement deploy;
    @FindBy(how=How.ID,using="btn-remove")
    public WebElement remove;
    @FindBy(id="btn-startup")
    public WebElement startup;
    @FindBy(id="btn-shutdown")
    public WebElement shutdown;
    @FindBy(id = "btn-startDeploy")
    public WebElement startDeploy;
    @FindBy(id="deploy_ok")
    public WebElement deploy_ok;
    @FindBy(id="btn-unload")
    public WebElement unload;

    public FormPage openFormByAdd()
    {
        tools.click(addBtn);
        return new FormPage();
    }
    public FormPage add()
    {
        tools.click(addBtn);
        return  new FormPage();
    }
    public FormPage edit()
    {
        tools.click(editBtn);
        return  new FormPage();
    }
    public  void  delete()
    {
        tools.click(delBtn);
        tools.alertAccept();
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
    public void unload()
    {
        tools.click(unload);
        tools.alertAccept();
        tools.click(deploy_ok);
    }
    public void deploy()
    {
        tools.click(deploy);
        tools.click(startDeploy);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
