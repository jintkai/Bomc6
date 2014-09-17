package com.code.page.ibnmsConfig.alarmPolicy.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmExpressionPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmUpgradeFrame;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.ExpressionAssertPage;
import com.code.page.ibnmsConfig.kpilist.KpiListFramePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/8/13.
 */
public class UpAlarmFormPage extends Page {
    public UpAlarmFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public String title="告警升级策略配置";
    @FindBy(name="policyName")
    WebElement policyName;
    @FindBy(name="policyDesc")
    WebElement policyDesc;

    AlarmUpgradeFrame alarmUpgrade=new AlarmUpgradeFrame();
    @FindBy(id="unitNamePrefix")
    WebElement unitNamePrefix;
    @FindBy(id = "btn-select-kpi")
    WebElement selectKpiBtn;
    @FindBy(id="btn-submit")
    WebElement submitBtn;
    ExpressionAssertPage expressAssert=new ExpressionAssertPage();
    KpiListFramePage kpiList=new KpiListFramePage(eventDriver);


    public void operate(Map<String,String> map)
    {
        tools.sendKeys(policyName,tools.getMapValue(map,"策略名称"));
        tools.sendKeys(policyDesc,tools.getMapValue(map,"策略描述"));
        alarmUpgrade.operate(map);
        if (!tools.getMapValue(map,"应用资源范围").isEmpty())
        {
            tools.sendKeys(unitNamePrefix,tools.getMapValue(map,"应用资源范围"));
            actions.sendKeys(Keys.ARROW_DOWN);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actions.sendKeys(Keys.ENTER).perform();
        }
        if (!tools.getMapValue(map,"指标编号_KPI").isEmpty())
        {
            tools.openModelDialog(selectKpiBtn);
            String hand=tools.switchToWindowByTitle(kpiList.title);
            kpiList.search(map);
            new GridPage(eventDriver).selectTr(1);
            kpiList.kpiBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.openModelDialog(submitBtn);
        String hand=tools.switchToWindowByTitle(expressAssert.title);
        expressAssert.submit();
        tools.switchToWindowByHand(hand);
        tools.alertAccept();
    }
}
