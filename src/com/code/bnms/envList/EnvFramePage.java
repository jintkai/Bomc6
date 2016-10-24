package com.code.bnms.envList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.envList.domain.EnvSearchDomain;
import com.code.bnms.kbplist.page.KbpTreePage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/15.
 */
public class EnvFramePage extends Page {
    KbpTreePage kbpTree=new KbpTreePage(eventDriver);
    String entityQueryFrame="entityQueryFrame";
    public String title="监控指标列表";
    public EnvListPage listPage=new EnvListPage(eventDriver);
    public EnvFramePage(EventFiringWebDriver eventDriver){
        super(eventDriver);
    }
    @Deprecated
    public GridPage search(Map<String,String> map)
    {
        tools.switchToFrame(entityQueryFrame);
        return listPage.search(map);
    }
    public GridPage search(EnvSearchDomain searchDomain)
    {
        tools.switchToFrame(entityQueryFrame);
        return listPage.search(searchDomain);
    }
}
