package com.code.page.workstation;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.workstation.page.WKBtnPage;
import com.code.page.workstation.page.WKFormPage;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class WKlistPage extends Page {
    WKFormPage wkForm=new WKFormPage();
    WKBtnPage wkBtn=new WKBtnPage();
    public void addWK(Map<String,String> map)
    {
        wkBtn.openFormByAdd();
        wkForm.addWK(map);
    }
    public void editWK(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")).get(0));
        wkBtn.editForm();
        wkForm.editWK(map);
    }
    public void delWK(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")).get(0));
        wkBtn.deleteForm();
    }
    public void deployWK(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")).get(0));
        wkBtn.deploy();
    }
    public void removeWK(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")).get(0));
        wkBtn.unloadWK();
    }
    public void startWK(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")).get(0));
        wkBtn.startup();
    }
    public void shutdownWK(Map<String,String> map)
    {
        GridPage gridTable=new GridPage();
        gridTable.selectTr(gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")).get(0));
        wkBtn.shutdown();
    }
}
