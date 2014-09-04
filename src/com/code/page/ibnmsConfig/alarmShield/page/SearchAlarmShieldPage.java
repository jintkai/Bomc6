package com.code.page.ibnmsConfig.alarmShield.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/9/4.
 */
public class SearchAlarmShieldPage extends Page {
    @FindBy(name="FILTER_NAME")
    WebElement filter_name;
    @FindBy(id = "btn-search")
    WebElement searchBtn;
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(filter_name,tools.getMapValue(map,"过滤器名称_SHIELD"));
        tools.click(searchBtn);
        return  new GridPage();
    }
}
