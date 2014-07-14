package com.code.page.PfList;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.PfList.page.PfBtnPage;
import com.code.page.PfList.page.PfFormPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class PFListPage extends Page {
    public PfBtnPage pfBtn=new PfBtnPage();
    public PfFormPage pfForm=new PfFormPage();
    public void addPf(Map<String,String> map)
    {
        pfBtn.openFormByAdd();
        pfForm.addPF(map);
    }
    public void editPf(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"选择名称")).get(0));
        //pfBtn.select();
        pfBtn.editForm();
        pfForm.addPF(map);
    }
    public void delPf(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"选择名称")).get(0));
        //pfBtn.select();
        pfBtn.deleteForm();
    }

    public void deploy(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"选择名称")).get(0));
        pfBtn.deploy();
    }
    public void remove(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"选择名称")).get(0));
        pfBtn.remove();
    }
    public void start(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"选择名称")).get(0));
        pfBtn.startup();
    }
    public void shutdown(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"选择名称")).get(0));
        pfBtn.shutdown();
    }
}
