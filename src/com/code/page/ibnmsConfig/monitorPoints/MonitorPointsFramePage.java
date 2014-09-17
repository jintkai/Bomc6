package com.code.page.ibnmsConfig.monitorPoints;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.kbplist.page.KbpTreePage;
import com.code.page.ibnmsConfig.monitorPoints.page.MonitorPointsListPage;

import java.util.Map;

/**
 * Created by Jin on 2014/9/10.
 * 监控点列表：http://localhost:8080/ibnms/config/monitorPointsFrame.action?opr=selectMulti
 */
public class MonitorPointsFramePage extends Page {
    public String title="监控点列表（多选）";
    MonitorPointsListPage monitorPointlist=new MonitorPointsListPage();
    KbpTreePage kbpTree=new KbpTreePage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);
    public GridPage searchMonitorPoints(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(monitorPointlist.iframeid);
        return monitorPointlist.search(map);
    }

    public void selectMonitorPoints(Map<String,String> map)
    {
        gridTable=searchMonitorPoints(map);
        gridTable.selectTr(1);
        monitorPointlist.btnPage.select();

    }
}
