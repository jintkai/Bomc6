package com.code.page.ibnmsConfig.alarmCorrelation.page;

import com.code.common.Page;
import com.code.page.ibnmsConfig.monitorPoints.MonitorPointsFramePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/9/11.
 */
public class AlarmCorrelationCfgPage extends Page {
    public AlarmCorrelationCfgPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public String title="数据库宕告警关联配置";
    @FindBy(name="alarmCorrelationCfg.sourceUnitId")
    WebElement sourceUnitId;
    @FindBy(id="alarmCorrelationCfg.resumeTime")
    WebElement resumeTime;
    @FindBy(id="btn-add")
    WebElement addBtn;
    @FindBy(id="btn-submit")
    WebElement submitBtn;

    public void operate(Map<String,String> map)
    {
        input(map);
    }
    public void input(Map<String,String> map)
    {
        tools.sendKeys(sourceUnitId,tools.getMapValue(map,"目标UNITID"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(Keys.ARROW_DOWN );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(Keys.ENTER ).perform();
        tools.sendKeys(resumeTime,tools.getMapValue(map,"恢复时间"));
        String moniterPointe=tools.getMapValue(map,"监控点增加");
        if (!moniterPointe.isEmpty() && Integer.parseInt(moniterPointe)!=0)
        {
            MonitorPointsFramePage monitorFrame=new MonitorPointsFramePage();
            Map<String,String> tempMap=map;
            for (int i=0;i<Integer.parseInt(moniterPointe);i++)
            {
                tools.openModelDialog(addBtn);
                String hand=tools.switchToWindowByTitle(monitorFrame.title);
                tempMap.put("监控点名称_MONITORPOINT",tools.getMapValue(map,"监控点名称_MONITORPOINT"+(i+1)));
                tempMap.put("指标名称_MONITORPOINT",tools.getMapValue(map,"指标名称_MONITORPOINT"+(i+1)));
                tempMap.put("监控点KBP_MONITORPOINT",tools.getMapValue(map,"监控点KBP_MONITORPOINT"+(i+1)));
                tempMap.put("指标ID_MONITORPOINT",tools.getMapValue(map,"指标ID_MONITORPOINT"+(i+1)));
                monitorFrame.selectMonitorPoints(tempMap);
                tools.switchToWindowByHand(hand);
            }
        }
        tools.click(submitBtn);
        tools.alertAccept();
    }
}
