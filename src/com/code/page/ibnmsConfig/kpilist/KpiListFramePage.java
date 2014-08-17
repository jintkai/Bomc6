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
    public String title="Kpi列表";
    public String kpiListIFrame="kpiListFrame";

    KpiTreePage kpiTree=new KpiTreePage();
    SearchKpiPage searchKpi=new SearchKpiPage();
    GridPage gridTable=new GridPage();
    public KpiBtnPage kpiBtn=new KpiBtnPage();
    KpiFormPage kpiForm=new KpiFormPage();
    public GridPage search(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(kpiListIFrame);
        return searchKpi.search(map);
    }
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(0);
        kpiTree.searchByTree(tools.getMapValue(map,"KBP"));
        tools.switchToFrame();
        tools.switchToFrame(kpiListIFrame);
        return new GridPage();
    }

    public GridPage operateKpi(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.contains("增加"))
        {
            tools.switchToFrame();
            tools.switchToFrame(0);
            this.kpiTree.searchByTree(tools.getMapValue(map, "KBP前缀"));
            tools.switchToFrame();
            tools.switchToFrame(kpiListIFrame);
            kpiBtn.add();
            return kpiForm.operateKpi(map);
        }
        if (operation.contains("修改"))
        {
            tools.switchToFrame();
            tools.switchToFrame(kpiListIFrame);
            searchKpi.search(map);
            gridTable.selectTr(0);
            kpiBtn.edit();
            return kpiForm.operateKpi(map);
        }
        else {
            tools.switchToFrame();
            tools.switchToFrame(kpiListIFrame);
            searchKpi.search(map);
            gridTable.selectTr(0);
            kpiBtn.delete();
            return gridTable;
        }
    }
}
