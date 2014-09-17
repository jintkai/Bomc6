package com.code.page.ibnmsConfig.workstation;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.workstation.page.WKBtnPage;
import com.code.page.ibnmsConfig.workstation.page.WKFormPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class WKlistPage extends Page {

    WKFormPage wkForm=new WKFormPage(eventDriver);
    WKBtnPage wkBtn=new WKBtnPage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);
    public WKlistPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public GridPage deployWK(Map<String,String> map)
    {
        String option=tools.getMapValue(map,"操作类型");
        gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
        if (option.equals("部署"))
            wkBtn.deploy();
        if(option.equals("卸载"))
            wkBtn.unload();
        if (option.equals("启动"))
            wkBtn.startup();
        if (option.equals("停止"))
            wkBtn.shutdown();
        return gridTable;
    }
    public GridPage operateWK(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加"))
        {
            wkBtn.add();
            return wkForm.operateWK(map);
        }
        if (operation.equals("修改"))
        {
            GridPage gridTable=new GridPage(eventDriver);
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            wkBtn.edit();
            return wkForm.operateWK(map);
        }
        else
        {
            GridPage gridTable=new GridPage(eventDriver);
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            wkBtn.delete();
            return gridTable;
        }

    }
}
