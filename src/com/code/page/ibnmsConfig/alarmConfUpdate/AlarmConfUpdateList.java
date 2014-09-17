package com.code.page.ibnmsConfig.alarmConfUpdate;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfUpdate.page.SearchUpdatePage;
import com.code.page.ibnmsConfig.alarmConfUpdate.page.UpdateBtnPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;
import java.util.Set;

/**
 * Created by jinkai on 2014/7/20.
 */

/**
 * 告警变更配置信息；http://172.21.0.31:8084/ibnms/config/inputEventUpdateInfoList.action
 */
public class AlarmConfUpdateList extends Page {
    public AlarmConfUpdateList(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    SearchUpdatePage searchUpdata=new SearchUpdatePage(eventDriver);
    UpdateBtnPage updateBtn=new UpdateBtnPage(eventDriver);
    public GridPage search(Map<String,String> map)
    {
        return searchUpdata.search(map);
    }
    public String title=" 告警变更配置项";

    public String update()
    {
        updateBtn.updata();
        return tools.getDriver().getCurrentUrl();
    }
    public String startTask()
    {
        GridPage gridTable=new GridPage(eventDriver);
        gridTable.selectTr(1);
        updateBtn.startTask();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> lists=tools.getDriver().getWindowHandles();
        String[] hands=new String[lists.size()];
        lists.toArray(hands);
        for (int i=0;i<hands.length;i++)
        {
            tools.switchToWindowByHand(hands[i]);
            String str= tools.getDriver().getTitle();
            if (!str.contains("告警变更配置项"))
                break;
        }
        //tools.switchToWindowByTitle("itil");
        return tools.getDriver().getCurrentUrl();
    }
    public void delete()
    {
        GridPage gridTable=new GridPage(eventDriver);
        gridTable.selectTr(1);
        updateBtn.delete();
    }
}
