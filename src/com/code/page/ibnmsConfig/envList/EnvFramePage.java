package com.code.page.ibnmsConfig.envList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.kbplist.page.KbpTreePage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/15.
 */
public class EnvFramePage extends Page {
    KbpTreePage kbpTree=new KbpTreePage();
    String entityQueryFrame="entityQueryFrame";
    public String title="监控指标列表";
    public EnvListPage listPage=new EnvListPage();
    public GridPage search(Map<String,String> map)
    {
        tools.switchToFrame(entityQueryFrame);
        return listPage.search(map);
    }
}
