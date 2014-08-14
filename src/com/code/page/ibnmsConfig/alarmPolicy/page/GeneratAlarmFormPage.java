package com.code.page.ibnmsConfig.alarmPolicy.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmExpressionPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmPolicyPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.ExpressionAssertPage;
import com.code.page.ibnmsConfig.kpilist.KpiListFramePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/7/30.
 */
public class GeneratAlarmFormPage extends Page {
    public String title="告警生成策略配置";
    WebElement policyName;
    WebElement policyDesc;

    AlarmExpressionPage alarmExpression=new AlarmExpressionPage();

    WebElement unitNamePrefix;
    @FindBy(id = "btn-select-kpi")
    WebElement selectKpiBtn;
    @FindBy(id="btn-submit")
    WebElement submitBtn;
    ExpressionAssertPage expressAssert=new ExpressionAssertPage();
    KpiListFramePage kpiList=new KpiListFramePage();


    public void operate(Map<String,String> map)
    {
        tools.sendKeys(policyName,tools.getMapValue(map,"策略名称"));
        tools.sendKeys(policyDesc,tools.getMapValue(map,"策略描述"));
        alarmExpression.operate(map);
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
            new GridPage().selectTr(1);
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
