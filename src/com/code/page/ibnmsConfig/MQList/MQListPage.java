package com.code.page.ibnmsConfig.MQList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.common.Tools;
import com.code.page.ibnmsConfig.MQList.Page.MQBtnPage;
import com.code.page.ibnmsConfig.MQList.Page.MQFormPage;
import com.code.page.ibnmsConfig.MQList.domain.MqFormDomain;
import com.code.page.ibnmsConfig.MQList.domain.MqSearchDomain;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class MQListPage extends Page {
    public MQListPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    MQBtnPage mqBtn=new MQBtnPage(eventDriver);
    MQFormPage mqForm=new MQFormPage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);

    public GridPage operateMQ(Map<String,String> map)
    {
        //GridPage gridTable=new GridPage();
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加"))
        {
            mqBtn.add();
            return mqForm.operateMQ(map);
        }
        if (operation.equals("修改"))
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            mqBtn.edit();
            tools.alertAccept();
            tools.alertAccept();
            return mqForm.operateMQ(map);
        }
        else
        {
            //删除
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            mqBtn.delete();
            return gridTable;
        }
    }
    public GridPage deploy(Map<String,String> map)
    {
        ArrayList<Integer> arrayList= gridTable.getListOftr(tools.getMapValue(map, "列表选择器"),tools.getMapValue(map, "列表匹配数据"));
        gridTable.selectTrs(arrayList);
        if (tools.getMapValue(map,"操作类型").equals("部署"))
            mqBtn.deploy();
        if (tools.getMapValue(map,"操作类型").equals("卸载"))
            mqBtn.remove();
        if (tools.getMapValue(map,"操作类型").equals("启动"))
            mqBtn.startup();
        if (tools.getMapValue(map,"操作类型").equals("停止"))
            mqBtn.shutdown();
        return gridTable;
    }
    public GridPage deploy(String operate,Map<String,String> map)
    {
        ArrayList<Integer> arrayList= gridTable.getListOftr(tools.getMapValue(map, "列名"),tools.getMapValue(map, "列值"));
        gridTable.selectTrs(arrayList);
        if (operate.equals("部署"))
            mqBtn.deploy();
        if (operate.equals("卸载"))
            mqBtn.remove();
        if (operate.equals("启动"))
            mqBtn.startup();
        if (operate.equals("停止"))
            mqBtn.shutdown();
        return gridTable;
    }

    public GridPage operateMQ(String operation, MqFormDomain mqFormDomain)
    {
        //GridPage gridTable=new GridPage();

        if (operation.equals("增加"))
        {
            mqBtn.add();
            return mqForm.operateMQ("增加",mqFormDomain);
        }
        if (operation.equals("修改"))
        {
            //gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            mqBtn.edit();
            tools.alertAccept();
            tools.alertAccept();
            return mqForm.operateMQ("修改",mqFormDomain);
        }
        else
        {
            //删除
            //gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            mqBtn.delete();
            return gridTable;
        }
    }
    public GridPage deploy(String operate, MqSearchDomain mqSearchDomain)
    {
//        ArrayList<Integer> arrayList= gridTable.getListOftr(tools.getMapValue(map, "列表选择器"),tools.getMapValue(map, "列表匹配数据"));
//        gridTable.selectTrs(arrayList);
//        if (tools.getMapValue(map,"操作类型").equals("部署"))
//            mqBtn.deploy();
//        if (tools.getMapValue(map,"操作类型").equals("卸载"))
//            mqBtn.remove();
//        if (tools.getMapValue(map,"操作类型").equals("启动"))
//            mqBtn.startup();
//        if (tools.getMapValue(map,"操作类型").equals("停止"))
//            mqBtn.shutdown();
        return gridTable;
    }
}
