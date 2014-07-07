package com.code.page.reslist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.reslist.page.ResTreePage;
import com.code.page.reslist.page.SearchResPage;

/**
 * Created by jinkai on 07/07/2014.
 */
public class ResListFramePage extends Page{
    ResTreePage resTree=new ResTreePage();
    SearchResPage searchRes=new SearchResPage();
    GridPage gridTable=new GridPage();
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
}
