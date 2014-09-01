package com.code.page.ibnmsConfig.templateDev;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.templateDev.page.TemplateDevListBtnPage;
import com.code.page.ibnmsConfig.templateDev.page.TemplateDevListSearchPage;

import java.util.Map;

/**
 * Created by Jin on 2014/8/21.
 * 告警模板与资源关联列表
 */
public class TemplateDevListPage extends Page{
    public String title="告警模版与资源关联列表";
    TemplateDevListSearchPage devSearch=new TemplateDevListSearchPage();
    TemplateDevListBtnPage devBtn=new TemplateDevListBtnPage();
    GridPage gridTable=new GridPage();
    public GridPage search(Map<String,String> map)
    {
        return devSearch.search(map);
    }
    public GridPage operate(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (!operation.equals("增加")) {
            gridTable = search(map);
            gridTable.selectTr(0);
        }
        if (operation.equals("删除"))
            devBtn.delete();
        if (operation.equals("二次同步"))
            devBtn.sync();
        if (operation.equals("增加"))
        {
            operateDevSet(map);
        }
        return gridTable;
    }
    public void operateDevSet(Map<String,String> map)
    {
        devBtn.devSet();
        TemplateDevSetPage devSet=new TemplateDevSetPage();
        String hand=tools.switchToWindowByTitle(devSet.title);
        Map<String,String> tempMap=map;
        tempMap.put("资源UNIT_ID_TEMPLATEDEV",tools.getMapValue(map,"资源UNIT_ID_TEMPLATEDEV_SET"));
        tempMap.put("资源名称_TEMPLATEDEV",tools.getMapValue(map,"资源名称_TEMPLATEDEV_SET"));
        tempMap.put("IP_TEMPLATEDEV",tools.getMapValue(map,"IP_TEMPLATEDEV_SET"));
        gridTable=devSet.search(tempMap);
        gridTable.selectTr(0);
        devSet.btn.save();
        tools.alertAccept();
        tools.switchToWindowByHand(hand);
    }
    public int searchDevSet(Map<String,String> map)
    {
        devBtn.devSet();
        TemplateDevSetPage devSet=new TemplateDevSetPage();
        String hand=tools.switchToWindowByTitle(devSet.title);
        gridTable=devSet.search(map);
        int row=gridTable.getRowNum();
        devSet.btn.cancel();
        tools.switchToWindowByHand(hand);
        return row;
    }

}
