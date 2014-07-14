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
    KpiFormPage kpiForm=new KpiFormPage();
    /*public GridPage searchKpiByTree(String str)
    {
        tools.switchToFrame(0);
        gridTable=kpiTree.searchByTree(str);
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
            kpiTree.searchByTree(tools.getMapValue(map, "父节点"));
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
    }*/
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
        gridTable.selectTrs(gridTable.getListOftr("指标名称",tools.getMapValue(map,"选择名称")));
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
