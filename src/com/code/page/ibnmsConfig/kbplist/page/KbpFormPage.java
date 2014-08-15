package com.code.page.ibnmsConfig.kbplist.page;

import com.code.common.Data;
import com.code.common.FormPage;


import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/6/22.
 * Kbp编辑页面
 */
public class KbpFormPage extends Page {
    WebElement classAfter;
    @FindBy(id="kbp.kbpCaption")
    WebElement kbpCaption;
    @FindBy(name="kbp.kbpDesc")
    WebElement kbpDesc;
    @FindBy(id= "kbp.middle")
    WebElement middle;
    WebElement kbp_viewStyle;
    WebElement kbp_enable;
    public GridPage operateKbp(Map<String,String> map)
    {
        inputForm(map);
        return new GridPage();
    }

    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(classAfter,tools.getMapValue(map,"编号"));
        tools.sendKeys(kbpCaption,tools.getMapValue(map,"名称"));
        tools.sendKeys(kbpDesc,tools.getMapValue(map,"KBP描述"));
        tools.selectByVisibleText(kbp_viewStyle,tools.getMapValue(map,"展现样式"));
        tools.selectByVisibleText(kbp_enable,tools.getMapValue(map,"是否有效"));
        tools.submit(kbpCaption);
    }
}