package com.code.page.ibnmsConfig.alarmTemplate.page;

import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmClearPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmExpressionPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmFilterPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmUpgradeFrame;
import com.code.page.ibnmsConfig.alarmPolicy.AlarmPolicyList;
import com.code.page.ibnmsConfig.kpilist.KpiListFramePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

/**
 * Created by Jin on 2014/8/19.
 */
public class eventTemplateKpiForm extends Page {


    public AlarmExpressionPage alarmExpression=new AlarmExpressionPage();
    public AlarmClearPage alarmClear=new AlarmClearPage();
    public AlarmUpgradeFrame alarmUpgrade=new AlarmUpgradeFrame();
    public AlarmFilterPage alarmFilter=new AlarmFilterPage();

    @FindBy(className = "btn-select-kpi")
    WebElement selectKpiBtn;

    String confTypeName="eventTemplateKpiForm.configType";

    @FindBy(id = "btn-submit")
    WebElement savaBtn;
    public void operate(Map<String,String> map)
    {
        tools.openModelDialog(selectKpiBtn);
        KpiListFramePage kpiFrame=new KpiListFramePage();
        String hand=tools.switchToWindowByTitle(kpiFrame.title);
        kpiFrame.search(map).selectTr(1);
        kpiFrame.kpiBtn.select();
        tools.switchToWindowByHand(hand);

        List<WebElement> confType=tools.findElements(tools.getDriver(),By.name(confTypeName));
        String confTypeStr=tools.getMapValue(map,"配置类型");
        for (int i=0;i<confType.size();i++)
        {
            if (confTypeStr.contains(tools.getAttribute(confType.get(i),"value")))
            {
                tools.click(confType.get(i));
                break;
            }
        }
        String temp=tools.getMapValue(map,"使用策略或模板_告警生成");
        Map<String,String> tempMap=map;
        tempMap.put("使用策略或模板",temp);
        temp=tools.getMapValue(map,"策略名称_ALAARMPOLICY_告警生成");
        tempMap.put("策略名称_ALAARMPOLICY",temp);
        alarmExpression.selectPolicy(tempMap);

        temp=tools.getMapValue(map,"使用策略或模板_告警升级");
        tempMap.put("使用策略或模板",temp);
        temp=tools.getMapValue(map,"策略名称_ALAARMPOLICY_告警升级");
        tempMap.put("策略名称_ALAARMPOLICY",temp);
        alarmUpgrade.selectPolicy(tempMap);

        tools.setScroll(500);
        temp=tools.getMapValue(map,"使用策略或模板_告警过滤");
        tempMap.put("使用策略或模板",temp);
        temp=tools.getMapValue(map,"策略名称_ALAARMPOLICY_告警过滤");
        tempMap.put("策略名称_ALAARMPOLICY",temp);
        alarmFilter.selectPolicy(tempMap);

        temp=tools.getMapValue(map,"使用策略或模板_告警清除");
        tempMap.put("使用策略或模板",temp);
        temp=tools.getMapValue(map,"策略名称_ALAARMPOLICY_告警清除");
        tempMap.put("策略名称_ALAARMPOLICY", temp);
        alarmClear.selectPolicy(tempMap);

        tools.click(savaBtn);

    }

}
