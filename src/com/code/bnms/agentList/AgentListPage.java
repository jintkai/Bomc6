package com.code.bnms.agentList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.agentList.domain.AgentFormDomain;
import com.code.bnms.agentList.domain.AgentSearchDomain;
import com.code.bnms.agentList.page.AgBtnPage;
import com.code.bnms.agentList.page.AgFormPage;
import com.code.bnms.agentList.page.SearchAGPage;
import com.code.bnms.collBusiConfig.CollBusiConfigPage;
import com.code.bnms.collBusiConfig.domain.ShellFormDomain;
import com.code.bnms.collBusiConfig.domain.SqlFormDomain;
import com.code.common.PageInfo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.lang.reflect.Field;
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

    @FindBy(id="btn-busiConfig")
    WebElement busiConfig;

    public GridPage search(Map<String,String> map)
    {
        return agSearch.search(map);
    }
    public GridPage search(AgentSearchDomain agentSearchDomain)
    {
        return agSearch.search(agentSearchDomain);
    }

    @Deprecated
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
        if(operation.equals("业务采集配置"))
            busiConfig.click();
        return gridTable;
    }

    public GridPage addBusi(String operation, Map<String,String> map, Object formDomain)
    {
        ArrayList<Integer> arrayList= gridTable.getListOftr(tools.getMapValue(map, "列名"),tools.getMapValue(map, "列值"));
        gridTable.selectTrs(arrayList);

        if(operation.equals("SHELL")) {
            busiConfig.click();
            CollBusiConfigPage collBusiConfigPage=new CollBusiConfigPage(eventDriver);
            collBusiConfigPage.addShellColl((ShellFormDomain) formDomain);
        }
        if(operation.equals("SQL")) {
            busiConfig.click();
            CollBusiConfigPage collBusiConfigPage=new CollBusiConfigPage(eventDriver);
            collBusiConfigPage.addSQLColl((SqlFormDomain) formDomain);
        }
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

        if (operation.equals("修改"))
        {
            gridTable.selectTr(1);
            agBtn.edit();
            (new AgFormPage(eventDriver)).getPageInfo();

            return null;
        }
        /*
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
