package com.code.page.ibnmsConfig.agentList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.agentList.domain.AgentFormDomain;
import com.code.page.ibnmsConfig.agentList.domain.AgentSearchDomain;
import com.code.page.ibnmsConfig.agentList.page.AgBtnPage;
import com.code.page.ibnmsConfig.agentList.page.AgFormPage;
import com.code.page.ibnmsConfig.agentList.page.SearchAGPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import sun.management.Agent;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgentListPage extends Page {
    public AgentListPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    AgBtnPage agBtn=new AgBtnPage(eventDriver);
    SearchAGPage agSearch=new SearchAGPage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);
    AgFormPage agForm=new AgFormPage(eventDriver);
    public GridPage search(Map<String,String> map)
    {
        return agSearch.search(map);
    }
    public GridPage search(AgentSearchDomain agentSearchDomain)
    {
        return agSearch.search(agentSearchDomain);
    }

    public GridPage deployAG(Map<String,String> map)
    {
        gridTable=agSearch.search(map);
        gridTable.selectTr(0);
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("部署"))
            agBtn.deploy();
        if (operation.equals("卸载"))
        {agBtn.remove();
        }
        if (operation.equals("启动"))
            agBtn.startup();
        if (operation.equals("停止"))
            agBtn.shutdown();
        return gridTable;
    }
    public GridPage deployAG(String operation,Map<String,String> map)
    {
        ArrayList<Integer> arrayList= gridTable.getListOftr(tools.getMapValue(map, "列名"),tools.getMapValue(map, "列值"));
        gridTable.selectTrs(arrayList);
        if (operation.equals("部署"))
            agBtn.deploy();
        if (operation.equals("卸载"))
        {agBtn.remove();
        }
        if (operation.equals("启动"))
            agBtn.startup();
        if (operation.equals("停止"))
            agBtn.shutdown();
        return gridTable;
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
            return gridTable;
        }
    }
    public GridPage operateAG(String operation, AgentFormDomain agentFormDomain)
    {
        if (operation.equals("增加"))
        {
            agBtn.add();
            return agForm.operateAG(operation,agentFormDomain);
        }
        /*
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
            return gridTable;
        }
        */
        return null;
    }
}
