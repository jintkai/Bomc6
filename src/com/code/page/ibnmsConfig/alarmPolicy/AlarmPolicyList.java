package com.code.page.ibnmsConfig.alarmPolicy;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmConfListPage;
import com.code.page.ibnmsConfig.alarmPolicy.page.AlarmPolicyBtnPage;
import com.code.page.ibnmsConfig.alarmPolicy.page.GeneratFormPage;
import com.code.page.ibnmsConfig.alarmPolicy.page.SearchAlarmPolicyPage;
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
    public GridPage search(Map<String,String> map)
    {
        searchPolicy.search(map);
        return new GridPage();
    }

    /**
     * 绑定数
     * @param map
     */
    public int bind(Map<String,String> map)
    {
        GridPage gridTable=searchPolicy.search(map);
        int td=gridTable.HeadIndex(tools.getMapValue(map,"可点击列名"));
        WebElement tdElement=gridTable.getTdEleOfTr(td, 0);
        tools.click(tools.findBy(tdElement, By.tagName("a")));
        String hand="";
        if (tools.getMapValue(map,"可点击列表").contains("模板"))
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
        tools.closeWindow();
        tools.switchToWindowByHand(hand);
        return row;
    }
    public void addGenerat(Map<String,String> map)
    {
        policyBtn.addGenerat();
        //String hand=tools.switchToWindowByTitle("告警生成");
        String hand=tools.switchToWindowByTitle("告警生成");
        System.out.println(tools.getDriver().getCurrentUrl());
        new GeneratFormPage().add(map);
        tools.switchToWindowByHand(hand);
    }
    public void edit(Map<String,String> map)
    {
        search(map);
        new GridPage().selectAllTr();
        policyBtn.edit();
        String hand=tools.switchToWindowByTitle("告警生成");
        System.out.println(tools.getDriver().getCurrentUrl());
        new GeneratFormPage().add(map);
        tools.switchToWindowByHand(hand);
    }
    public void delete(Map<String,String> map)
    {
        search(map);
        new GridPage().selectAllTr();
        policyBtn.delete();
    }

}
