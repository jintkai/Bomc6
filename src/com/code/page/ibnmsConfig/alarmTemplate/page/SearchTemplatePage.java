package com.code.page.ibnmsConfig.alarmTemplate.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/8/4.
 */
public class SearchTemplatePage  extends Page{
    @FindBy(name="filter.templateName")
    WebElement templateName;
    @FindBy(id = "filter_templateType")
    WebElement templateType;
    @FindBy(id = "btn-search")
    WebElement searchBtn;
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(templateName,tools.getMapValue(map,"模板名称_ALARMTEMPLATE"));
        tools.selectByVisibleText(templateType,tools.getMapValue(map,"模板类型_ALARMTEMPLATE"));
        tools.click(searchBtn);
        return new GridPage();
    }
}
