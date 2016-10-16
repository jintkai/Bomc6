package com.code.page.ibnmsConfig.envList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;
import com.code.page.ibnmsConfig.envList.page.EnvBtnPage;
import com.code.page.ibnmsConfig.envList.page.EnvFormPage;
import com.code.page.ibnmsConfig.envList.page.SearchEnvPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 * 部署环境Page
 */
public class EnvListPage extends Page {
    public EnvListPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public SearchEnvPage searchEnv=new SearchEnvPage(eventDriver);
    public EnvBtnPage envBtn=new EnvBtnPage(eventDriver);
    public GridPage gridTable=new GridPage(eventDriver);
    EnvFormPage envForm=new EnvFormPage(eventDriver);

    public GridPage search(Map<String,String> map)
    {
        return searchEnv.search(map);
    }
    public GridPage search(EnvSearchDomain o){
        return searchEnv.search(o);
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
            return new GridPage(eventDriver);
        }
    }
}
