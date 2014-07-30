package com.code.page.ibnmsConfig.alarmPolicy.page;

import com.code.common.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jin on 2014/7/30.
 */
public class AlarmPolicyBtnPage extends Page {
    @FindBy(id="btn-add")
    WebElement addBtn;
    @FindBy(id="btn-add-select")
    WebElement addSelectBtn;
    @FindBy(id="btn-edit")
    WebElement editBtn;
    @FindBy(id="btn-del")
    WebElement delBtn;
    @FindBy(id="btn-sync")
    WebElement syncBtn;
    public void addGenerat()
    {
        tools.moveToElement(addBtn);
        //tools.selectByVisibleText(addSelectBtn,"告警生成策略");
        actions.sendKeys(Keys.ARROW_DOWN);
        actions.sendKeys(Keys.ENTER).perform();
    }

}
