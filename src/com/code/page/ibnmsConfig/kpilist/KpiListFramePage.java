package com.code.page.ibnmsConfig.kpilist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.kpilist.page.KpiBtnPage;
import com.code.page.ibnmsConfig.kpilist.page.KpiFormPage;
import com.code.page.ibnmsConfig.kpilist.page.KpiTreePage;
import com.code.page.ibnmsConfig.kpilist.page.SearchKpiPage;

import java.util.Map;

/**
 * Created by jinkai on 06/07/2014.
 */
public class KpiListFramePage extends Page{
    KpiTreePage kpiTree=new KpiTreePage();
    SearchKpiPage searchKpi=new SearchKpiPage();
    GridPage gridTable=new GridPage();
    public KpiBtnPage kpiBtn=new KpiBtnPage();
    KpiFormPage kpiForm=new KpiFormPage();
    public GridPage search(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("kpiListFrame");
        return searchKpi.search(map);
    }
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(0);
        kpiTree.searchByTree(tools.getMapValue(map,"KBP"));
        return new GridPage();
    }
    public GridPage add(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(0);
        this.kpiTree.searchByTree(tools.getMapValue(map, "KBP前缀"));
        kpiBtn.add();
        kpiForm.add(map);
        return  new GridPage();
    }
    public GridPage edit(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("kpiListFrame");
        int row=gridTable.selectTrs(gridTable.getListOftr("指标名称",tools.getMapValue(map,"选择名称")));
        tools.assertEquals(row,0);
        kpiBtn.edit();
        kpiForm.edit(map);
        return  new GridPage();
    }
    public GridPage delete(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("kpiListFrame");
        searchKpi.search(map);
        gridTable.selectAllTr();
        kpiBtn.delete();
        return  new GridPage();
    }
}
