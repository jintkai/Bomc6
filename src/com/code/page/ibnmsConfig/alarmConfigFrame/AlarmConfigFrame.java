package com.code.page.ibnmsConfig.alarmConfigFrame;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmConfBtnPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmConfFormPage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmConfTreePage;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmSearchHeadPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/16.
 * 告警集中配置；
 */
public class AlarmConfigFrame extends Page {
    public AlarmConfTreePage confTree=new AlarmConfTreePage();
    public AlarmConfBtnPage confBtn=new AlarmConfBtnPage();
    public AlarmSearchHeadPage searchHead=new AlarmSearchHeadPage();
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(0);
        confTree.searchByTree(tools.getMapValue(map,"TREE"));
        tools.switchToFrame();
        tools.switchToFrame(1);
        return new GridPage();
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
    public GridPage searchByHead(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(1);
        return searchHead.searchByHead(map);
    }
}
