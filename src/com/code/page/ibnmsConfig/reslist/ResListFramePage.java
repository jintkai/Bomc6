package com.code.page.ibnmsConfig.reslist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.reslist.page.ResBtnPage;
import com.code.page.ibnmsConfig.reslist.page.ResFormPage;
import com.code.page.ibnmsConfig.reslist.page.ResTreePage;
import com.code.page.ibnmsConfig.reslist.page.SearchResPage;

import java.util.Map;

/**
 * Created by jinkai on 07/07/2014.
 */
public class ResListFramePage extends Page{
    ResTreePage resTree=new ResTreePage();
    SearchResPage searchRes=new SearchResPage();
    public GridPage gridTable=new GridPage();
    public ResBtnPage resBtn=new ResBtnPage();

    public GridPage searchResByTree(String treeValue)
    {
        tools.switchToFrame(0);
        resTree.searchByTree(treeValue);
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        return  new GridPage();
    }
    public GridPage searchResByUnitID(String id)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        return searchRes.searchByUnitId(id);
    }
    public GridPage searchResByName(String name)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        return searchRes.searchByName(name);
    }
    public GridPage searchResByIp(String ip)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        return searchRes.searchByIp(ip);
    }
    public GridPage searchResByType(String type)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        return searchRes.searchByType(type);
    }
    public GridPage searchResByMulit(String id,String name,String ip,String type)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        return searchRes.searchByMulti(id,name,ip,type);
    }
    public void searchRes(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        searchRes.searchRes(map);

    }
    public GridPage addRes(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        resBtn.openFormByAdd();
        new ResFormPage().addRes(map);
        return searchRes.searchByName(tools.getMapValue(map,"资源名称"));
    }
    public GridPage editRes(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        searchRes.searchByUnitId(tools.getMapValue(map,"UNIT_ID")).selectTr(0);
        resBtn.editForm();
        new ResFormPage().editRes(map);
        return searchRes.searchByName(tools.getMapValue(map,"资源名称"));
    }
    public GridPage delRes(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        searchRes.searchByUnitId(tools.getMapValue(map,"UNIT_ID")).selectTr(0);
        resBtn.deleteForm();
        return searchRes.searchByUnitId(tools.getMapValue(map,"UNIT_ID"));
    }
    public GridPage search(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        return searchRes.search(map);
    }
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(0);
        resTree.searchByTree(tools.getMapValue(map, "TREE"));
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        return new GridPage();
    }
    public GridPage add(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        resBtn.add();
        new ResFormPage().add(map);
        return  new GridPage();
    }
    public GridPage edit(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        gridTable.selectTrs(gridTable.getListOftr("资源名称",tools.getMapValue(map,"选择名称")));
        resBtn.edit();
        new ResFormPage().add(map);
        return  new GridPage();
    }
    public GridPage delete(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("resListFrame");
        gridTable.selectTrs(gridTable.getListOftr("资源名称",tools.getMapValue(map,"选择名称")));
        resBtn.delete();
        //new ResFormPage().add(map);
        return  new GridPage();
    }
}
