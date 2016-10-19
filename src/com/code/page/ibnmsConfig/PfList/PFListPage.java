package com.code.page.ibnmsConfig.PfList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.PfList.domain.PfFormDomain;
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
    public GridPage deployPF(String operation,Map<String,String> map)
    {
        gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")));
        if (operation.equals("部署"))
            pfBtn.deploy();
        if (operation.equals("卸载"))
        {
            pfBtn.remove();
        }
        if (operation.equals("启动"))
            pfBtn.startup();
        if (operation.equals("停止"))
            pfBtn.shutdown();
        return gridTable;
    }

    public GridPage operatePF(String operation, PfFormDomain domain,Map map)
    {


        if (operation.equals("增加"))
        {
            pfBtn.add();
            return  pfForm.operatePF(domain);
        }
        if (operation.equals("修改"))
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")));
            pfBtn.edit();
            return  pfForm.operatePF(domain);
        }
        else
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")));
            pfBtn.delete();
            return gridTable;
        }
    }
}
