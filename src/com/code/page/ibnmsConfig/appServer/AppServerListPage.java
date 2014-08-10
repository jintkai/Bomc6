package com.code.page.ibnmsConfig.appServer;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.appServer.page.AppBtnPage;
import com.code.page.ibnmsConfig.appServer.page.AppFormPage;
import com.code.page.ibnmsConfig.appServer.page.AppSearchPage;

import java.util.Map;

/**
 * Created by Jin on 2014/8/10.
 */
public class AppServerListPage extends Page {
    AppBtnPage appBtn=new AppBtnPage();
    AppSearchPage appSearch=new AppSearchPage();
    AppFormPage appForm=new AppFormPage();
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
            GridPage gridTable=new GridPage();
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            appBtn.edit();
            return  appForm.operateApp(map);
        }
        else
        {
            GridPage gridTable=new GridPage();
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            appBtn.delete();
            return new GridPage();
        }
    }
    public GridPage deployApp(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
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
        return  new GridPage();
    }
}
