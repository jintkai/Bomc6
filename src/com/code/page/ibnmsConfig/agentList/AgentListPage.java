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
    public GridPage add(Map<String,String> map)
    {
        agBtn.add();
        return new AgFormPage().add(map);
    }
    public GridPage edit(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")).get(0));
        agBtn.editForm();
        new AgFormPage().add(map);
        return  new GridPage();
    }
    public GridPage delete(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Agent名称", tools.getMapValue(map, "选择名称")).get(0));
        agBtn.delete();
        tools.alertAccept();
        return new GridPage();
    }
    public GridPage search(Map<String,String> map)
    {
        return agSearch.search(map);
    }

    public GridPage deploy(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")));
        if (tools.getMapValue(map,"操作类型").equals("部署"))
            agBtn.deploy();
        if (tools.getMapValue(map,"操作类型").equals("卸载"))
            agBtn.remove();
        if (tools.getMapValue(map,"操作类型").equals("启动"))
            agBtn.startup();
        if (tools.getMapValue(map,"操作类型").equals("停止"))
            agBtn.shutdown();
        return new GridPage();
    }

}
