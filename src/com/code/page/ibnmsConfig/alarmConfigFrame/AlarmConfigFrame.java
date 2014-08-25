package com.code.page.ibnmsConfig.alarmConfigFrame;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmConfBtnPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmConfFormPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmConfTreePage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmSearchHeadPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/16.
 * 告警集中配置；
 */
public class AlarmConfigFrame extends Page {
    public AlarmConfTreePage confTree=new AlarmConfTreePage();
    public AlarmConfBtnPage confBtn=new AlarmConfBtnPage();
    public AlarmSearchHeadPage searchHead=new AlarmSearchHeadPage();
    public GridPage gridTable=new GridPage();
    AlarmConfFormPage alarmConfForm=new AlarmConfFormPage();
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(0);
        confTree.searchByTree(tools.getMapValue(map,"TREE"));
        tools.switchToFrame();
        tools.switchToFrame(1);
        return gridTable;
    }
    public GridPage searchByHead(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(1);
        return gridTable=searchHead.searchByHead(map);
    }

    public void addConf(Map<String,String> map,int type)
    {
        searchByTree(map);
        AlarmConfFormPage alarmConfForm=confBtn.addConf(tools.getMapValue(map, "告警类型"));
        alarmConfForm.add(map,type);
    }

    public void edit(Map<String,String> map,int type)
    {
        GridPage gridTable=searchByHead(map);
        gridTable.selectAllTr();

        while(!gridTable.selectBt.isSelected())
        {
            gridTable.selectAllTr();
        }
        confBtn.edit();
        AlarmConfFormPage alarmConfForm=new AlarmConfFormPage();
        //AlarmConfFormPage alarmConfForm=confBtn.addConf(tools.getMapValue(map, "告警类型"));
        alarmConfForm.add(map,type);

    }
    public void delete(Map<String,String> map)
    {
        GridPage gridTable=searchByHead(map);
        gridTable.selectAllTr();

        while(!gridTable.selectBt.isSelected())
        {
            gridTable.selectAllTr();
        }
        confBtn.delete();
        tools.alertAccept();
    }

    public void exportExcel()
    {
        tools.switchToFrame();
        tools.switchToFrame(1);
        confBtn.exportExcel();
    }
    public void openEditedList()
    {
        tools.switchToFrame();
        tools.switchToFrame(1);
        confBtn.openEditedList();
    }
    public void openFilteredList()
    {
        tools.switchToFrame();
        tools.switchToFrame(1);
        confBtn.openFilteredList();
    }
    public int addFilter()
    {
        tools.switchToFrame();
        tools.switchToFrame(1);
        GridPage gridTable=new GridPage();
        System.out.println(gridTable.getWebElementTr(1));
        gridTable.selectTr(1);
        confBtn.addfilter();
        List<WebElement> list=gridTable.getWebElementTr(1).findElements(By.cssSelector("span[title*='待过滤']"));
        return  list.size();
    }
    public  void  queryFilterAdd(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(1);
        searchByHead(map);
        if(tools.getMapValue(map,"操作类型").equals("增加"))
            confBtn.queryFilterAdd(tools.getMapValue(map,"过滤器名称"));
        if(tools.getMapValue(map,"操作类型").equals("删除"))
            confBtn.queryFilterRemove(tools.getMapValue(map,"过滤器名称"));
    }

}
