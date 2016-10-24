package com.code.bnms.envList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.envList.domain.EnvFormDomain;
import com.code.bnms.envList.domain.EnvSearchDomain;
import com.code.bnms.envList.page.EnvBtnPage;
import com.code.bnms.envList.page.EnvFormPage;
import com.code.bnms.envList.page.SearchEnvPage;
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
    @Deprecated
    public GridPage search(Map<String,String> map)
    {
        return searchEnv.search(map);
    }
    public GridPage search(EnvSearchDomain o){
        return searchEnv.search(o);
    }
    @Deprecated
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

    public GridPage operateRes(String operation, EnvSearchDomain envSearchDomain, EnvFormDomain envFormDomain)
    {

        if (operation.equals("增加"))
        {
            envBtn.add();
            return envForm.operateEnv("增加",null,envFormDomain);
        }
        if (operation.equals("修改"))
        {

            GridPage gridTable=searchEnv.search(envSearchDomain);
            gridTable.selectTr(0);
            envBtn.edit();
            return envForm.operateEnv("修改",envSearchDomain,envFormDomain);
        }
        else
        {
            GridPage gridTable=searchEnv.search(envSearchDomain);
            gridTable.selectTr(0);
            envBtn.delete();
            return new GridPage(eventDriver);
        }
    }
}
