package com.code.page.ibnmsConfig.workstation;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.workstation.page.WKBtnPage;
import com.code.page.ibnmsConfig.workstation.page.WKFormPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class WKlistPage extends Page {
    WKFormPage wkForm=new WKFormPage();
    WKBtnPage wkBtn=new WKBtnPage();

    public GridPage add(Map<String,String> map)
    {
        wkBtn.add();
        return  new WKFormPage().add(map);
    }
    public GridPage edit(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")));
        wkBtn.edit();
        new WKFormPage().add(map);
        return  new GridPage();
    }
    public GridPage delete(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")));
        wkBtn.delete();
        return  new GridPage();
    }
    public void deploy(Map<String,String> map)
    {
        String option=tools.getMapValue(map,"操作类型");
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")));
        if (option.equals("部署"))
            wkBtn.deploy();
        if(option.equals("卸载"))
            wkBtn.unload();
        if (option.equals("启动"))
            wkBtn.startup();
        if (option.equals("停止"))
            wkBtn.shutdown();
    }
}
