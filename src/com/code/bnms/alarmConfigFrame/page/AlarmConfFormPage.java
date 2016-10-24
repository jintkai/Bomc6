package com.code.bnms.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/16.
 * 告警集中配置增加页面
 */
public class AlarmConfFormPage extends Page {
    public String mainFrame="alarmConfigFrame";
    //public AlarmPolicyPage alarmPolicy=new AlarmPolicyPage();
    public MonitorPointPage monitorPoint=new MonitorPointPage();
    public AlarmUpgradeFrame upgrade=new AlarmUpgradeFrame();
    public AlarmFilterPage filter=new AlarmFilterPage();
    public AlarmClearPage clear=new AlarmClearPage();
    @FindBy(xpath="//button[@data-type=\"save\"]")
    WebElement save;
    @FindBy(xpath="//button[@data-type=\"saveas\"]")
    WebElement saveEdit;
    AlarmExpressionPage expressionPage=new AlarmExpressionPage();

    public Map<String,String> getMap(Map<String,String> map,String str)
    {
        Map<String,String> tempMap=map;
        //str="告警升级";
        tempMap.put("使用策略或模板",tools.getMapValue(map,"使用策略或模板_"+str));
        tempMap.put("策略名称_ALAARMPOLICY",tools.getMapValue(map,"策略名称_ALAARMPOLICY_"+str));
        tempMap.put("泛函数",tools.getMapValue(map,"泛函数_"+str));
        tempMap.put("isInMonth_month",tools.getMapValue(map,"isInMonth_month_"+str));
        tempMap.put("compareKpiValueByUK_kpiId",tools.getMapValue(map,"compareKpiValueByUK_kpiId_"+str));
        tempMap.put("compareKpiValueByUK_unitId",tools.getMapValue(map,"compareKpiValueByUK_unitId_"+str));
        tempMap.put("compareKpiValueByUK_compare_type",tools.getMapValue(map,"compareKpiValueByUK_compare_type_"+str));
        tempMap.put("compareKpiValueByUK_compare_value",tools.getMapValue(map,"compareKpiValueByUK_compare_value_"+str));
        tempMap.put("overByTimes_m",tools.getMapValue(map,"overByTimes_m_"+str));
        tempMap.put("overByTimes_n",tools.getMapValue(map,"overByTimes_n_"+str));
        tempMap.put("overByTimes_threshold",tools.getMapValue(map,"overByTimes_threshold_"+str));
        return  tempMap;
    }

    public void add(Map<String,String> map,int type)
    {


        monitorPoint.add(map);
        String policyStr=tools.getMapValue(map,"使用策略或模板_告警生成");
        Map<String,String> tempMap=map;
        tempMap.put("使用策略或模板",policyStr);
        tempMap.put("策略名称_ALAARMPOLICY",tools.getMapValue(map,"策略名称_ALAARMPOLICY_告警生成"));
        if (policyStr.isEmpty()) {
            tools.switchToFrame("generationFrame");
        }
        expressionPage.alarmForm.setUseOtherID("useOther");
        expressionPage.operate(tempMap);
        tools.switchToFrame();
        tools.switchToFrame(mainFrame);


        tempMap=getMap(map,"告警升级");
        policyStr=tools.getMapValue(tempMap,"使用策略或模板_告警升级");
        if(policyStr.isEmpty())
            tools.switchToFrame("upgradeFrame");
        upgrade.alarmForm.setUseOtherID("useOther");
        upgrade.setPolicyId("upgrade");
        upgrade.add(tempMap);
        tools.switchToFrame();
        String str="var a =window.top.document.getElementById(\"alarmConfigFrame\").contentWindow.document.body.scrollTop=600";
        tools.execJS(" var a=document.getElementsByTagName(\"iframe\")[1].contentWindow.document.documentElement.scrollTop=600;return a;");
        tools.switchToFrame(mainFrame);

        tempMap=getMap(map,"告警过滤");
        policyStr=tools.getMapValue(tempMap,"使用策略或模板_告警过滤");
        if(policyStr.isEmpty())
            tools.switchToFrame("filterFrame");
        filter.alarmForm.setUseOtherID("useOther");
        filter.setPolicyId("filter");
        filter.add(map);
        tools.switchToFrame();
        str="var a =window.top.document.getElementById(\"alarmConfigFrame\").contentWindow.document.body.scrollTop=600";
        tools.execJS(" var a=document.getElementsByTagName(\"iframe\")[1].contentWindow.document.documentElement.scrollTop=600;return a;");
        tools.switchToFrame(mainFrame);

        tempMap=getMap(map,"告警清除");
        policyStr=tools.getMapValue(tempMap,"使用策略或模板_告警清楚");
        if(policyStr.isEmpty())
            tools.switchToFrame("clearFrame");
        clear.alarmForm.setUseOtherID("useOther");
        clear.setPolicyId("clear");
        clear.add(map);
        tools.switchToFrame();
        tools.switchToFrame(mainFrame);

        if (type==1)
            tools.click(save);
        if (type==2)
            tools.click(saveEdit);

        String hand=tools.switchToWindowByTitle("表达式验证");
        new ExpressionAssertPage().submit();
        tools.switchToWindowByHand(hand);
        tools.alertAccept();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
