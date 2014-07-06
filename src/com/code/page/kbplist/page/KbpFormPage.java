package com.code.page.kbplist.page;

import com.code.common.GridTablePage;
import com.code.common.Page;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

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

    public void addKBP(Map<String,String> map)
    {
        tools.sendKeys(classAfter,map.get("KBP编号"));
        tools.sendKeys(kbpCaption,map.get("KBP名称"));
        tools.sendKeys(kbpDesc,map.get("KBP描述"));
       //middle.sendKeys("006");
        tools.SelectByVisibleText(viewStyle,map.get("展现样式"));
        tools.SelectByVisibleText(enable,map.get("是否有效"));
        tools.click(btn);
    }
    public void editKbp(String caption)
    {
        tools.sendKeys(kbpCaption,caption);
        tools.click(btn);
    }

}
