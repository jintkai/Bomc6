package com.code.page.ibnmsConfig.alarmConfUpdate;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfUpdate.page.SearchUpdataPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/20.
 */

/**
 * 告警变更配置信息；http://172.21.0.31:8084/ibnms/config/inputEventUpdateInfoList.action
 */
public class AlarmConfUpdateList extends Page {
    SearchUpdataPage searchUpdata=new SearchUpdataPage();
    public GridPage search(Map<String,String> map)
    {
        return searchUpdata.search(map);
    }
}
