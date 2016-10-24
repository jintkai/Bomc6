package com.code.bnms.alarmPolicy.page;

import com.code.common.BtnPage;
import com.code.common.FormPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/7/30.
 */
public class AlarmPolicyBtnPage extends BtnPage {
    public AlarmPolicyBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
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
    public void openAddForm(Map<String,String> map)
    {
        String addType=tools.getMapValue(map,"策略类型");
        tools.moveToElement(addBtn);
        if (addType.equals("告警生成策略"))
            actions.sendKeys(Keys.ARROW_DOWN);
        if (addType.equals("预警生成策略"))
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN);
        if (addType.equals("告警升级策略"))
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN);
        if (addType.equals("告警过滤策略"))
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN);
        if (addType.equals("告警清除策略"))
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN);
        if (addType.equals("告警订阅策略"))
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN);

        actions.sendKeys(Keys.ENTER).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete()
    {
        tools.click(delBtn);
        tools.alertAccept();
        tools.alertAccept();
    }
    @Override
    public FormPage edit()
    {
        tools.openModelDialog(editBtn);
        return new FormPage(eventDriver);
    }
    public void sysAlarm()
    {
        //tools.click(syncBtn);
        tools.openModelDialog(syncBtn);
    }
    public void view()
    {
        tools.openModelDialog(this.viewBtn);
    }
}
