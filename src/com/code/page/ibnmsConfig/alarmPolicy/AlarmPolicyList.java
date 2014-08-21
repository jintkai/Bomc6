package com.code.page.ibnmsConfig.alarmPolicy;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmConfListPage;
import com.code.page.ibnmsConfig.alarmPolicy.page.*;
import com.code.page.ibnmsConfig.alarmTemplate.AlarmTemplateListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

/**
 * Created by Jin on 2014/7/30.
 * 告警策略维护列表
 */
public class AlarmPolicyList extends Page {
    public String title="策略维护列表";
    SearchAlarmPolicyPage searchPolicy=new SearchAlarmPolicyPage();
    public AlarmPolicyBtnPage policyBtn=new AlarmPolicyBtnPage();
    GeneratAlarmFormPage generatAlarm=new GeneratAlarmFormPage();
    PreGeneratAlarmFormPage preGeneratAlarm=new PreGeneratAlarmFormPage();
    UpAlarmFormPage upAlarm=new UpAlarmFormPage();
    FilterAlarmFormPage filterAlarm=new FilterAlarmFormPage();
    ClearAlarmFormPage clearAlarm=new ClearAlarmFormPage();
    GridPage gridTable=new GridPage();
    //告警集中配置列表
    AlarmConfListPage alaremConfList=new AlarmConfListPage();
    public GridPage search(Map<String,String> map)
    {
        searchPolicy.search(map);
        return gridTable;
    }

    /**
     * 绑定数
     * @param map
     */
    public int bind(Map<String,String> map)
    {
        gridTable=searchPolicy.search(map);
        int td=gridTable.HeadIndex(tools.getMapValue(map,"可点击列名"));
        WebElement tdElement=gridTable.getTdEleOfTr(td, 0);
        WebElement a=tools.findBy(tdElement, By.tagName("a"));
        String hand=tools.getDriver().getWindowHandle();
        if (tools.getMapValue(map,"可点击列名").contains("未同步"))
            tools.openModelDialog(a);
        else
            tools.click(a);

        if (tools.getMapValue(map,"可点击列名").contains("模板"))
        {
           AlarmTemplateListPage alarmTemplateList=new AlarmTemplateListPage();
           hand=tools.switchToWindowByTitle(alarmTemplateList.title);

        }
           else
        {
            AlarmConfListPage alarmConfList=new AlarmConfListPage();
            hand=tools.switchToWindowByTitle(alarmConfList.title);
        }
        int row=new GridPage().getRowNum();
        String js="window.open(\"\", \"_self\");window.close();";
        tools.execJS(js);
        tools.switchToWindowByHand(hand);
        return row;
    }

    public void addPolicy(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if(operation.equals("增加"))
            policyBtn.openAddForm(map);
        if(operation.equals("修改"))
        {
            search(map);
            gridTable.selectTr(1);
            policyBtn.edit();
        }
        if (operation.equals("删除"))
        {
            search(map);
            gridTable.selectTr(1);
            policyBtn.delete();
            return;
        }
        String addType=tools.getMapValue(map,"策略类型");
        String hand="";
        if (addType.equals("告警生成策略")) {
            hand = tools.switchToWindowByTitle(generatAlarm.title);
            generatAlarm.operate(map);
        }
        if (addType.equals("预警生成策略")) {
            hand = tools.switchToWindowByTitle(preGeneratAlarm.title);
            preGeneratAlarm.operate(map);
        }
        if(addType.equals("告警升级策略"))
        {
            hand=tools.switchToWindowByTitle(upAlarm.title);
            upAlarm.operate(map);
        }
        if(addType.equals("告警过滤策略"))
        {
            hand=tools.switchToWindowByTitle(filterAlarm.title);
            filterAlarm.operate(map);
        }
        if(addType.equals("告警清除策略"))
        {
            hand=tools.switchToWindowByTitle(clearAlarm.title);
            clearAlarm.operate(map);
        }
        tools.switchToWindowByHand(hand);
    }
    public GridPage sysAlarm(Map<String,String> map)
    {
        search(map);
        gridTable.selectTr(1);
        policyBtn.sysAlarm();
        String hand=tools.switchToWindowByTitle(alaremConfList.title);
        new GridPage().selectTr(0);
        alaremConfList.btnPage.select();
        tools.alertAccept();
        tools.alertAccept();
        tools.click(alaremConfList.btnPage.closeBtn);
        tools.switchToWindowByHand(hand);
        search(map);
        //gridTable.getTrOfAllTd(1);
        return gridTable;
    }
    public String  viewAlarm(Map<String,String> map)
    {
        search(map);
        gridTable.selectTr(1);
        policyBtn.view();
        String policyName="";
        String hand=tools.switchToWindowByTitle("警");
        if (tools.getMapValue(map,"策略类").contains("订阅"))
           // policyName=tools.getAttribute(generatAlarm.policyName,"value");
            System.out.println("无法查看订阅人策略");
        else
            policyName=tools.getAttribute(generatAlarm.policyName,"value");
        generatAlarm.cancel();
        tools.switchToWindowByHand(hand);
        return policyName;
    }
}
