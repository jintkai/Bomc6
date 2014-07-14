package com.code.page.agentList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.agentList.page.AgBtnPage;
import com.code.page.agentList.page.AgFormPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgentListPage extends Page {
    AgBtnPage agBtn=new AgBtnPage();

    public void addAg(Map<String,String> map)
    {
        agBtn.openFormByAdd();
        new AgFormPage().addAg(map);
    }
    public void editAg(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")).get(0));
        agBtn.editForm();
        new AgFormPage().editAg(map);
    }
    public void delAg(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")).get(0));
        agBtn.deleteForm();
        tools.alertAccept();
    }
    public void deploy(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")).get(0));
        agBtn.deploy();
    }
    public void remove(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")).get(0));
        agBtn.remove();
    }
    public void start(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")).get(0));
        agBtn.startup();
    }
    public void shutdown(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")).get(0));
        agBtn.shutdown();
    }
}
