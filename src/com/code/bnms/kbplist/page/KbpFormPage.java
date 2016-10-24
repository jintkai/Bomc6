package com.code.bnms.kbplist.page;


import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.kbplist.domain.KbpFormDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/6/22.
 * Kbp编辑页面
 */
public class KbpFormPage extends Page {
    public KbpFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    WebElement classAfter;
    @FindBy(id="kbp.kbpCaption")
    WebElement kbpCaption;
    @FindBy(name="kbp.kbpDesc")
    WebElement kbpDesc;
    @FindBy(id= "middle")
    WebElement middle;
    WebElement kbp_viewStyle;
    WebElement kbp_enable;
    @Deprecated
    public GridPage operateKbp(Map<String,String> map)
    {
        inputForm(map);
        return new GridPage(eventDriver);
    }
    @Deprecated
    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(classAfter,tools.getMapValue(map,"编号"));
        tools.sendKeys(kbpCaption,tools.getMapValue(map,"名称"));
        tools.sendKeys(kbpDesc,tools.getMapValue(map,"KBP描述"));
        tools.selectByVisibleText(kbp_viewStyle,tools.getMapValue(map,"展现样式"));
        tools.selectByVisibleText(kbp_enable,tools.getMapValue(map,"是否有效"));
        tools.sendKeys(middle,tools.getMapValue(map,"关联KPI前缀"));
        tools.submit(kbpCaption);
    }

    public void inputForm(KbpFormDomain domain){
        tools.sendKeys(classAfter,domain.getKBP_CLASS());
        tools.sendKeys(kbpCaption,domain.getKBP_CAPTION());
        tools.sendKeys(kbpDesc,domain.getKBP_DESC());
        tools.selectByVisibleText(kbp_viewStyle,domain.getVIEW_STYLE());
        tools.selectByVisibleText(kbp_enable,domain.getENABLE());
        tools.sendKeys(middle,domain.getMiddle());
        tools.submit(kbpCaption);
    }
    public GridPage operateKbp(KbpFormDomain domain){
        inputForm(domain);
        return new GridPage(eventDriver);
    }
}
