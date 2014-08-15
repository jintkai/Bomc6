package com.code.page.ibnmsConfig.envList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.envList.page.EnvBtnPage;
import com.code.page.ibnmsConfig.envList.page.EnvFormPage;
import com.code.page.ibnmsConfig.envList.page.SearchEnvPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 * 部署环境Page
 */
public class EnvListPage extends Page {
    public SearchEnvPage searchEnv=new SearchEnvPage();
    public EnvBtnPage envBtn=new EnvBtnPage();
    public GridPage gridTable=new GridPage();
    EnvFormPage envForm=new EnvFormPage();

    public GridPage search(Map<String,String> map)
    {
        return searchEnv.search(map);
    }
    public GridPage operateRes(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加"))
        {
            envBtn.add();
            return envForm.operateEnv(map);
        }
        if (operation.equals("修改"))
        {

            GridPage gridTable=searchEnv.search(map);
            gridTable.selectTr(0);
            envBtn.edit();
            return envForm.operateEnv(map);
        }
        else
        {
            GridPage gridTable=searchEnv.search(map);
            gridTable.selectTr(0);
            envBtn.delete();
            return new GridPage();
        }
    }
}