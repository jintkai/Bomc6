package com.code.test.unicom.main.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Administrator on 16-6-1.
 */
public class NumRuleSearchPage extends Page {
    public NumRuleSearchPage(EventFiringWebDriver webDriver){
        super(webDriver);
    }
    @FindBy(how= How.ID,using="good_name")
    WebElement goodName;
    @FindBy(how=How.ID,using ="good_status")
    WebElement goodStatus;
    @FindBy(how=How.ID,using = "good_level")
    WebElement goodLevel;
    @FindBy(how=How.ID,using="btn_sub")
    WebElement btnSub;
    public void searchNumRule(Map<String,String> map)
    {
        tools.sendKeys(goodName,tools.getMapValue(map,"靓号规则名称"));
        tools.selectByVisibleText(goodStatus,tools.getMapValue(map,"规则状态"));
        tools.selectByVisibleText(goodLevel,tools.getMapValue(map,"靓号级别"));
        tools.click(btnSub);

    }
}
