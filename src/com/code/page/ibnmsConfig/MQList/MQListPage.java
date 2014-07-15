package com.code.page.ibnmsConfig.MQList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.MQList.Page.MQBtnPage;
import com.code.page.ibnmsConfig.MQList.Page.MQFormPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class MQListPage extends Page {
    MQBtnPage mqBtn=new MQBtnPage();
    MQFormPage mqForm=new MQFormPage();
    GridPage gridTable=new GridPage();

    public GridPage add(Map<String,String> map)
    {
        mqBtn.add();
        return mqForm.add(map);
    }
    public GridPage edit(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("MQ名称",tools.getMapValue(map,"选择名称")));
        mqBtn.edit();
        tools.alertAccept();
        tools.alertAccept();
        return mqForm.add(map);
    }
    public GridPage delete(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("MQ名称", tools.getMapValue(map, "选择名称")));
        mqBtn.delete();
        return gridTable;
    }
    public GridPage deploy(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("MQ名称",tools.getMapValue(map,"选择名称")));
        if (tools.getMapValue(map,"操作类型").equals("部署"))
            mqBtn.deploy();
        if (tools.getMapValue(map,"操作类型").equals("卸载"))
            mqBtn.remove();
        if (tools.getMapValue(map,"操作类型").equals("启动"))
            mqBtn.startup();
        if (tools.getMapValue(map,"操作类型").equals("停止"))
            mqBtn.shutdown();
        return new GridPage();
    }
}
