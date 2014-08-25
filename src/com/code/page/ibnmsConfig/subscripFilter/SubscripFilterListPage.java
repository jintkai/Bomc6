package com.code.page.ibnmsConfig.subscripFilter;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.subscripFilter.page.SearchFilterPage;
import com.code.page.ibnmsConfig.subscripFilter.page.SubscripFilterBtn;
import com.code.page.ibnmsConfig.subscripFilter.page.SubscripFilterFormPage;

import java.util.Map;
/**
 * Created by Jin on 2014/8/24.
 * 告警订阅过滤器
 */
public class SubscripFilterListPage  extends Page{

    public SearchFilterPage searchPage=new SearchFilterPage();
    public SubscripFilterBtn subscripBtn=new SubscripFilterBtn();
    public SubscripFilterFormPage subscripForm=new SubscripFilterFormPage();
    GridPage gridTable=new GridPage();
    public GridPage search(Map<String,String> map)
    {
        gridTable=searchPage.search(map);
        return gridTable;
    }
    public void operate(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加")) {
            subscripBtn.add(map);
            String hand = tools.switchToWindowByTitle(subscripForm.titile);
            subscripForm.inputForm(map);
            tools.switchToWindowByHand(hand);
        }
        if (operation.equals("修改")) {
            gridTable=searchPage.search(map);
            gridTable.selectTr(0);
            subscripBtn.edit();
            String hand = tools.switchToWindowByTitle(subscripForm.titile);
            subscripForm.inputForm(map);
            tools.switchToWindowByHand(hand);
        }
        if (operation.equals("删除"))
        {
            gridTable=searchPage.search(map);
            gridTable.selectTr(0);
            subscripBtn.delete();
        }

    }
}
