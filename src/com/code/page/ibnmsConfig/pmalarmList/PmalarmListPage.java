package com.code.page.ibnmsConfig.pmalarmList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.pmalarmList.page.PaBtnPage;
import com.code.page.ibnmsConfig.pmalarmList.page.PaFormPage;

import java.util.Map;

/**
 * Created by Jin on 2014/8/10.
 */
public class PmalarmListPage extends Page {
    PaBtnPage paBtn=new PaBtnPage();
    PaFormPage paForm=new PaFormPage();
    public GridPage operatePA(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加"))
        {
            paBtn.add();
            return paForm.operatePA(map);
        }
        if(operation.equals("修改"))
        {
            GridPage gridTable=new GridPage();
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            paBtn.edit();
            return  paForm.operatePA(map);
        }
        else
        {
            GridPage gridTable=new GridPage();
            gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
            paBtn.delete();
            return new GridPage();
        }
    }

    /**
     * 以下方法需要重载，PmAlarm不通用；
     * @param map
     * @return
     */
    public GridPage deployPA(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTrs(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")));
        if (tools.getMapValue(map,"操作类型").equals("部署"))
            paBtn.deploy();
        if (tools.getMapValue(map,"操作类型").equals("卸载"))
        {
            paBtn.remove();
        }
        if (tools.getMapValue(map,"操作类型").equals("启动"))
            paBtn.startup();
        if (tools.getMapValue(map,"操作类型").equals("停止"))
            paBtn.shutdown();
        return new GridPage();
    }
}
