package com.code.page.ibnmsConfig.agentList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.agentList.page.AgBtnPage;
import com.code.page.ibnmsConfig.agentList.page.AgFormPage;
import com.code.page.ibnmsConfig.agentList.page.SearchAGPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgentListPage extends Page {
    AgBtnPage agBtn=new AgBtnPage();
    SearchAGPage agSearch=new SearchAGPage();
    GridPage gridTable=new GridPage();
    AgFormPage agForm=new AgFormPage();

    public GridPage search(Map<String,String> map)
    {
        return agSearch.search(map);
    }

    public GridPage deployAG(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable=agSearch.search(map);
        gridTable.selectTr(0);
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("部署"))
            agBtn.deploy();
        if (operation.equals("卸载"))
        {agBtn.remove();
        tools.alertAccept();}
        if (operation.equals("启动"))
            agBtn.startup();
        if (operation.equals("停止"))
            agBtn.shutdown();
        return new GridPage();
    }
    public GridPage operateAG(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");

        if (operation.equals("增加"))
        {
            agBtn.add();
            return agForm.operateAG(map);
        }
        if (operation.equals("修改"))
        {
            agSearch.search(map).selectTr(0);
            agBtn.edit();
            return agForm.operateAG(map);
        }
        else
        {
            agSearch.search(map).selectTr(0);
            agBtn.delete();
            return  new GridPage();
        }
    }
}
