package com.code.page.ibnmsConfig.reslist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.reslist.domain.ResFormDomain;
import com.code.page.ibnmsConfig.reslist.domain.ResSearchDomain;
import com.code.page.ibnmsConfig.reslist.page.ResBtnPage;
import com.code.page.ibnmsConfig.reslist.page.ResFormPage;
import com.code.page.ibnmsConfig.reslist.page.ResTreePage;
import com.code.page.ibnmsConfig.reslist.page.SearchResPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 07/07/2014.
 */
public class ResListFramePage extends Page{
    public ResListFramePage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    ResTreePage resTree=new ResTreePage(eventDriver);
    SearchResPage searchRes=new SearchResPage(eventDriver);
    public GridPage gridTable=new GridPage(eventDriver);
    public ResBtnPage resBtn=new ResBtnPage(eventDriver);
    ResFormPage resForm=new ResFormPage(eventDriver);
    public String resListFrame="resListFrame";
    public String title="资源列表";
    public int treeIFrame=0;

    @Deprecated
    public GridPage search(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(resListFrame);
        return searchRes.search(map);
    }
    public GridPage search(ResSearchDomain domain)
    {
        tools.switchToFrame();
        tools.switchToFrame(resListFrame);
        return searchRes.search(domain);
    }
    @Deprecated
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(treeIFrame);
        resTree.searchByTree(tools.getMapValue(map, "TREE"));
        tools.switchToFrame();
        tools.switchToFrame(resListFrame);
        return new GridPage(eventDriver);
    }

    public GridPage searchByTree(String resName)
    {
        tools.switchToFrame();
        tools.switchToFrame(treeIFrame);
        resTree.searchByTree(resName);
        tools.switchToFrame();
        tools.switchToFrame(resListFrame);
        return new GridPage(eventDriver);
    }
    @Deprecated
    public GridPage operateRes(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(resListFrame);
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加"))
        {
            resBtn.add();
            return resForm.operateRes(map);
        }
        if (operation.equals("修改"))
        {
            GridPage gridTable=searchRes.search(map);
            gridTable.selectTr(0);
            resBtn.edit();
            return resForm.operateRes(map);
        }
        else
        {
            GridPage gridTable=searchRes.search(map);
            gridTable.selectTr(0);
            resBtn.delete();
            return new GridPage(eventDriver);
        }
    }

    public GridPage operateRes(String operation, ResSearchDomain searchDomain,ResFormDomain domain)
    {
        tools.switchToFrame();
        tools.switchToFrame(resListFrame);

        if (operation.equals("增加"))
        {
            resBtn.add();
            return resForm.operateRes("增加",domain);
        }
        if (operation.equals("修改"))
        {
            GridPage gridTable=searchRes.search(searchDomain);
            gridTable.selectTr(0);
            resBtn.edit();
            return resForm.operateRes("修改",domain);
        }
        else
        {
            GridPage gridTable=searchRes.search(searchDomain);
            gridTable.selectTr(0);
            resBtn.delete();
            return new GridPage(eventDriver);
        }
    }

}
