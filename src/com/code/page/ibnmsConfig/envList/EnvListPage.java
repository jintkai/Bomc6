package com.code.page.ibnmsConfig.envList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.envList.page.EnvBtnPage;
import com.code.page.ibnmsConfig.envList.page.EnvFormPage;
import com.code.page.ibnmsConfig.envList.page.SearchEnvPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class EnvListPage extends Page {
    public SearchEnvPage searchEnv=new SearchEnvPage();
    public EnvBtnPage envBtn=new EnvBtnPage();
    public GridPage gridTable=new GridPage();
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
    public GridPage search(Map<String,String> map)
    {
        return searchEnv.search(map);
    }
    public GridPage add(Map<String,String> map)
    {
        envBtn.add();
        return new EnvFormPage().add(map);
    }
    public GridPage edit(Map<String,String> map)
    {
        gridTable.selectTrs(gridTable.getListOftr("主机名称",tools.getMapValue(map,"选择名称")));
        envBtn.edit();
        return new EnvFormPage().add(map);
    }
    public GridPage delete(Map<String,String> map)
    {
        gridTable.selectTrs(gridTable.getListOftr("主机名称",tools.getMapValue(map,"选择名称")));
        envBtn.delete();
        return new GridPage();
    }
}
