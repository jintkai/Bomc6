package com.code.page.MQList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.MQList.Page.MQBtnPage;
import com.code.page.MQList.Page.MQFormPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class MQListPage extends Page {
    MQBtnPage mqBtn=new MQBtnPage();
    MQFormPage mqForm=new MQFormPage();
    GridPage gridTable=new GridPage();
    public void addMQ(Map<String,String> map)
    {
        mqBtn.openFormByAdd();
        mqForm.addMQ(map);
    }
    public void deployMQ(Map<String,String> map)
    {
        if(gridTable.getListOftr("MQ名称",tools.getMapValue(map,"MQ名称")).size()>0)
        {
            System.out.println("无法选择多个");
        }
        int i=gridTable.getListOftr("MQ名称",tools.getMapValue(map,"MQ名称")).get(0);
        gridTable.selectTr(i);
        if (tools.getMapValue(map,"MQ操作").equals("部署"))
        {
            mqBtn.deploy();
        }
        if (tools.getMapValue(map,"MQ操作").equals("卸载"))
        {
            mqBtn.remove();
        }
        if (tools.getMapValue(map,"MQ操作").equals("启动"))
        {
            mqBtn.startup();
        }
        if (tools.getMapValue(map,"MQ操作").equals("停止"))
        {
            mqBtn.shutdown();
        }
    }
}
