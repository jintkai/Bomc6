package com.code.page.ibnmsConfig.monitorPoints.page;

import com.code.common.BtnPage;
import com.code.common.GridPage;

import java.util.Map;

/**
 * Created by Jin on 2014/9/10.
 */
public class MonitorPointsListPage {
    public String iframeid="monitorPointsListFrame";
    GridPage gridTable=new GridPage();
    SearchMonitorPointPage searchMonitor=new SearchMonitorPointPage();
    public BtnPage btnPage=new BtnPage();
    public GridPage search(Map<String,String> map)
    {
        searchMonitor.search(map);
        gridTable=new GridPage();
        return  gridTable;
    }
}
