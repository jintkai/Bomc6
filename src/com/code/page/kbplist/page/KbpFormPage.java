package com.code.page.kbplist.page;

import com.code.common.Data;
import com.code.common.FormPage;


import com.code.common.GridPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/6/22.
 */
public class KbpFormPage extends FormPage {
    @FindBy(id= Data.classAfterID)
    WebElement classAfter;
    @FindBy(id= Data.kbpCaptionID)
    WebElement kbpCaption;
    @FindBy(name=Data.kbpDescName)
    WebElement kbpDesc;
    @FindBy(id= Data.middleID)
    WebElement middle;
    @FindBy(id= Data.viewStyleID)
    WebElement viewStyle;
    @FindBy(id= Data.enableID)
    WebElement enable;

    public GridPage addKBP(Map<String,String> map)
    {
        inputForm(map);
        return new GridPage();
    }

    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(classAfter,map.get("编号"));
        tools.sendKeys(kbpCaption,map.get("名称"));
        tools.sendKeys(kbpDesc,map.get("KBP描述"));
        tools.selectByVisibleText(viewStyle,map.get("展现样式"));
        tools.selectByVisibleText(enable,map.get("是否有效"));
        tools.submit(kbpCaption);
    }
}
