package com.code.page.ibnmsConfig.PfList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.PfList.page.PfBtnPage;
import com.code.page.ibnmsConfig.PfList.page.PfFormPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class PFListPage extends Page {

    public PfBtnPage pfBtn=new PfBtnPage(eventDriver);
    public PfFormPage pfForm=new PfFormPage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);
    public PFListPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public GridPage deployPF(Map<String,String> map)
    {
        gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
        if (tools.getMapValue(map,"操作类型").equals("部署"))
            pfBtn.deploy();
        if (tools.getMapValue(map,"操作类型").equals("卸载"))
        {
            pfBtn.remove();
        }
        if (tools.getMapValue(map,"操作类型").equals("启动"))
            pfBtn.startup();
        if (tools.getMapValue(map,"操作类型").equals("停止"))
            pfBtn.shutdown();
        return gridTable;
    }

    public GridPage operatePF(Map<String,String> map)
    {
        String operation=tools.getMapValue(map, "操作类型");

        if (operation.equals("增加"))
        {
            pfBtn.add();
            return  pfForm.operatePF(map);
        }
        if (operation.equals("修改"))
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            pfBtn.edit();
            return  pfForm.operatePF(map);
        }
        else
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            pfBtn.delete();
            return gridTable;
        }
    }
}
