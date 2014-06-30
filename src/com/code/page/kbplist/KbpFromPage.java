package com.code.page.kbplist;

import com.code.common.Page;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
/**
 * Created by jinkai on 2014/6/22.
 */
public class KbpFromPage extends Page {
    WebElement classAfter;
    @FindBy(how= How.ID,using="kbp.kbpCaption")
    WebElement kbpCaption;
    @FindBy(how=How.NAME,using="kbp.kbpDesc")
    WebElement kbpDesc;
    WebElement middle;
    @FindBy(how=How.ID,using="kbp_viewStyle")
    WebElement viewStyle;
    @FindBy(how=How.ID,using="kbp_enable")
    WebElement enable;
    @FindBy(how=How.ID,using="btn-submit")
    WebElement btn;

    public void init()
    {
        classAfter.clear();
        kbpCaption.clear();
        kbpDesc.clear();
    }
    public void addKBP(String obj[])
    {
        init();
        classAfter.sendKeys(obj[6]);
        kbpCaption.sendKeys(obj[7]);
        kbpDesc.sendKeys(obj[8]);
       //middle.sendKeys("006");
        Select select= new Select(viewStyle);
        select.selectByVisibleText(obj[10]);
        (new Select(enable)).selectByVisibleText(obj[11]);
        enable.submit();
        //btn.click();
    }

}
