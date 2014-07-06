package com.code.page.kpilist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.kpilist.page.KpiBtnPage;
import com.code.page.kpilist.page.KpiFormPage;
import com.code.page.kpilist.page.KpiTreePage;
import com.code.page.kpilist.page.SearchKpiPage;

import java.util.Map;

/**
 * Created by jinkai on 06/07/2014.
 */
public class KpiListFramePage extends Page{
    KpiTreePage kpiTree=new KpiTreePage();
    SearchKpiPage searchKpi=new SearchKpiPage();
    GridPage gridTable=new GridPage();
    KpiBtnPage kpiBtn=new KpiBtnPage();
    public GridPage searchKpiByTree(String str)
    {
        tools.switchToFrame(0);
        gridTable=kpiTree.searchKbpByTree(str);
        return  gridTable;
    }
    public GridPage searchKpiById(String id)
    {
        tools.switchToFrame();
        tools.switchToFrame("kpiListFrame");
        return gridTable=searchKpi.searchByID(id);
    }

    public GridPage searchKpiByName(String name)
    {
        tools.switchToFrame();
        tools.switchToFrame("kpiListFrame");
        return gridTable=searchKpi.searchByName(name);
    }

    public GridPage searchKpiByMulti(String id,String name)
    {
        tools.switchToFrame();
        tools.switchToFrame("kpiListFrame");
        return gridTable=searchKpi.searchByMulti(id, name);
    }
    public GridPage addKpi(Map<String,String> map)
    {
        tools.switchToFrame();
        if(!tools.getMapValue(map, "父节点").equals("0"))
        {
            kpiTree.searchKbpByTree(tools.getMapValue(map, "父节点"));
            tools.switchToFrame();
        }
        tools.switchToFrame("kpiListFrame");
        kpiBtn.openFormByAdd();
        KpiFormPage kpiForm= new KpiFormPage();
        kpiForm.addKpi(map);
        return searchKpi.searchByMulti(tools.getMapValue(map,"KPI编号"),tools.getMapValue(map,"KPI名称"));
    }
    public GridPage delKpi(String name)
    {
        tools.switchToFrame();
        tools.switchToFrame("kpiListFrame");
        searchKpi.searchByName(name).selectTr(0);
        kpiBtn.deleteForm();
        return searchKpi.searchByName(name);

    }
    public GridPage editKpi(String oldName,Map<String ,String > map)
    {
        tools.switchToFrame();
        tools.switchToFrame("kpiListFrame");
        searchKpi.searchByName(oldName).selectTr(0);
        kpiBtn.editForm();
        KpiFormPage kpiForm= new KpiFormPage();
        kpiForm.editKpi(map);
        return searchKpi.searchByName(tools.getMapValue(map,"KPI名称"));
    }
}
