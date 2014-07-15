package com.code.page.ibnmsConfig.PfList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.PfList.page.PfBtnPage;
import com.code.page.ibnmsConfig.PfList.page.PfFormPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class PFListPage extends Page {
    public PfBtnPage pfBtn=new PfBtnPage();
    public PfFormPage pfForm=new PfFormPage();

    public GridPage deploy(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"选择名称")));
        if (tools.getMapValue(map,"操作类型").equals("部署"))
            pfBtn.deploy();
        if (tools.getMapValue(map,"操作类型").equals("卸载"))
            pfBtn.remove();
        if (tools.getMapValue(map,"操作类型").equals("启动"))
            pfBtn.startup();
        if (tools.getMapValue(map,"操作类型").equals("停止"))
            pfBtn.shutdown();
        return new GridPage();
    }
    public GridPage add(Map<String,String> map)
    {
        pfBtn.add();
        return pfForm.add(map);
    }
    public GridPage edit(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("Performance名称", tools.getMapValue(map, "选择名称")));
        pfBtn.edit();
        return pfForm.add(map);
    }
    public GridPage delete(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("Performance名称", tools.getMapValue(map, "选择名称")));
        pfBtn.delete();
        return new GridPage();
    }
}
