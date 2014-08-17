package com.code.page.ibnmsConfig.MQList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.common.Tools;
import com.code.page.ibnmsConfig.MQList.Page.MQBtnPage;
import com.code.page.ibnmsConfig.MQList.Page.MQFormPage;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class MQListPage extends Page {
    MQBtnPage mqBtn=new MQBtnPage();
    MQFormPage mqForm=new MQFormPage();
    GridPage gridTable=new GridPage();

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
}
