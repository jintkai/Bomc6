package com.code.bnms.monitorPoints.page;

import com.code.common.BtnPage;
import com.code.common.GridPage;
import com.code.common.Page;

import java.util.Map;

/**
 * Created by Jin on 2014/9/10.
 */
public class MonitorPointsListPage extends Page {
    public String iframeid="monitorPointsListFrame";
    GridPage gridTable=new GridPage(eventDriver);
    SearchMonitorPointPage searchMonitor=new SearchMonitorPointPage();
    public BtnPage btnPage=new BtnPage(eventDriver);
    public GridPage search(Map<String,String> map)
    {
        searchMonitor.search(map);
        gridTable=new GridPage(eventDriver);
        return  gridTable;
    }
}
