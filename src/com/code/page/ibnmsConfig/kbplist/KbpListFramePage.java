package com.code.page.ibnmsConfig.kbplist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.kbplist.page.KbpBtnPage;
import com.code.page.ibnmsConfig.kbplist.page.KbpFormPage;
import com.code.page.ibnmsConfig.kbplist.page.KbpTreePage;
import com.code.page.ibnmsConfig.kbplist.page.SearchKbpPage;

import java.util.Map;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpListFramePage extends Page {

    private KbpTreePage kbpTree=new KbpTreePage();
    GridPage gridTable=new GridPage();
    public SearchKbpPage searchKbp=new SearchKbpPage();
    KbpFormPage kbpForm=new KbpFormPage();
    KbpBtnPage kbpBtn=new KbpBtnPage();

    public GridPage search(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("kbpListFrame");
        return this.searchKbp.search(map);
    }
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("kbpTree");
        return kbpTree.searchKpiByTree(tools.getMapValue(map, "KBP"));
    }
    public GridPage add(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(0);
        kbpTree.searchKpiByTree(tools.getMapValue(map,"KBP编号前缀"));
        kbpBtn.add();
        kbpForm=new KbpFormPage();
        return kbpForm.addKBP(map);
    }
    public GridPage edit(Map<String,String > map)
    {
        tools.switchToFrame();
        tools.switchToFrame("kbpListFrame");
        gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr("KBP名称", tools.getMapValue(map, "选择名称")));
        kbpBtn.edit();
        kbpForm=new KbpFormPage();
        return  kbpForm.addKBP(map);
    }
    public GridPage delete(Map<String,String > map)
    {
        tools.switchToFrame();
        tools.switchToFrame("kbpListFrame");
        searchKbp.search(map);
        gridTable=new GridPage();
        gridTable.selectAllTr();
        kbpBtn.delete();
        return  new GridPage();
    }

}