package com.code.page.envList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.envList.page.EnvBtnPage;
import com.code.page.envList.page.EnvFormPage;
import com.code.page.envList.page.SearchEnvPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class EnvListPage extends Page {
    public SearchEnvPage searchEnv=new SearchEnvPage();
    public EnvBtnPage envBtn=new EnvBtnPage();
    EnvFormPage envForm=new EnvFormPage();
    public void searchEnv(Map<String,String> map)
    {
        searchEnv.searchEnv(map);
    }
    public void searchByName(String name)
    {
        searchEnv.searchByName(name);
    }
    public void addEnv(Map<String,String> map)
    {
        envBtn.openFormByAdd();
        envForm.addEnv(map);
    }
    public void delEnv(Map<String,String> map)
    {
        searchEnv.searchEnv(map);
        new GridPage().selectTr(0);
        envBtn.deleteForm();
    }
}
