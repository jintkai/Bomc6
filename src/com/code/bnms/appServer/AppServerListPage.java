package com.code.bnms.appServer;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.appServer.domain.AppFormDomain;
import com.code.bnms.appServer.page.AppBtnPage;
import com.code.bnms.appServer.page.AppFormPage;
import com.code.bnms.appServer.page.AppSearchPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/8/10.
 */
public class AppServerListPage extends Page {
    public AppServerListPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    AppBtnPage appBtn=new AppBtnPage(eventDriver);
    AppSearchPage appSearch=new AppSearchPage(eventDriver);
    AppFormPage appForm=new AppFormPage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);
    @Deprecated
    public GridPage operateApp(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加"))
        {
            tools.selectByVisibleText(appSearch.applyModule,tools.getMapValue(map,"组建类型"));
            appBtn.add();
            return  appForm.operateApp(map);
        }
        if (operation.equals("修改"))
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            appBtn.edit();
            return  appForm.operateApp(map);
        }
        else
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            appBtn.delete();
            return gridTable;
        }
    }

    public GridPage deployApp(String operation,Map<String,String> map)
    {
        gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")));
        if (operation.equals("部署"))
        {
            appBtn.deploy();

        }
        if (operation.equals("卸载"))
        {
            appBtn.remove();
        }
        if (operation.equals("启动"))
        {
            appBtn.startup();
        }
        if (operation.equals("停止"))
        {
            appBtn.shutdown();
        }
        return  gridTable;
    }

    public GridPage operateApp(String operation, String applyModule,AppFormDomain formDomain,Map<String ,String > map)
    {
        if (operation.equals("增加"))
        {
            tools.selectByVisibleText(appSearch.applyModule,applyModule);
            appBtn.add();
            return  appForm.operateApp(formDomain);
        }
        if (operation.equals("修改"))
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")));
            appBtn.edit();
            return  appForm.operateApp(formDomain);
        }
        else
        {
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")));
            appBtn.delete();
            return gridTable;
        }
    }
}
