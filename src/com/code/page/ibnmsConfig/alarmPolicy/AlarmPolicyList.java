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
    SearchAlarmPolicyPage searchPolicy=new SearchAlarmPolicyPage();
    AlarmPolicyBtnPage policyBtn=new AlarmPolicyBtnPage();
    GeneratAlarmFormPage generatAlarm=new GeneratAlarmFormPage();
    PreGeneratAlarmFormPage preGeneratAlarm=new PreGeneratAlarmFormPage();
    UpAlarmFormPage upAlarm=new UpAlarmFormPage();
    FilterAlarmFormPage filterAlarm=new FilterAlarmFormPage();
    ClearAlarmFormPage clearAlarm=new ClearAlarmFormPage();
    GridPage gridTable=new GridPage();
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
    /**
    public void addGenerat(Map<String,String> map)
    {
        policyBtn.openAddForm(map);
        //String hand=tools.switchToWindowByTitle("告警生成");
        String hand=tools.switchToWindowByTitle("告警生成");
        System.out.println(tools.getDriver().getCurrentUrl());
        //new GeneratFormPage().add(map);
        tools.switchToWindowByHand(hand);
    }
    public void edit(Map<String,String> map)
    {
        search(map);
        new GridPage().selectAllTr();
        policyBtn.edit();
        String hand=tools.switchToWindowByTitle("告警生成");
        System.out.println(tools.getDriver().getCurrentUrl());
        //new GeneratFormPage().add(map);
        tools.switchToWindowByHand(hand);
    }
    public void delete(Map<String,String> map)
    {
        search(map);
        gridTable.selectAllTr();
        policyBtn.delete();
    }*/

    public void addPolicy(Map<String,String> map)
    {
        policyBtn.openAddForm(map);
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
}
