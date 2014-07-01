package com.code.page.kpilist;

import com.code.common.GridTablePage;
import com.code.common.Page;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KpiListPage extends Page{
    SearchKpiPage searchKpi=new SearchKpiPage();
    GridTablePage gridTable=new GridTablePage();
    public GridTablePage getGridTableByID(String id)
    {
        return gridTable=searchKpi.searchByID(id);
    }
    public GridTablePage getGridTableByName(String name)
    {
        return  gridTable=searchKpi.searchByName(name);
    }
    public GridTablePage getGridTableByMulit(String id,String name)
    {
        return gridTable=searchKpi.searchMulti(id,name);
    }
    public void  init()
    {
        searchKpi.init();
    }
}
