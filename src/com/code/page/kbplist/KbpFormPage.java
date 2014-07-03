package com.code.page.kbplist;

import com.code.common.GridTablePage;
import com.code.common.Page;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
/**
 * Created by jinkai on 2014/6/22.
 */
public class KbpFormPage extends Page {
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

    public void addKBP(String obj[])
    {
        tools.sendKeys(classAfter,obj[6]);
        tools.sendKeys(kbpCaption,obj[7]);
        tools.sendKeys(kbpDesc,obj[8]);
       //middle.sendKeys("006");
        tools.SelectByVisibleText(viewStyle,obj[10]);
        tools.SelectByVisibleText(enable,obj[11]);
        tools.click(btn);
    }

}
