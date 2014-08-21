package com.code.page.ibnmsConfig.alarmTemplate;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmTemplate.page.AlarmTemplateBtnPage;
import com.code.page.ibnmsConfig.alarmTemplate.page.SearchTemplatePage;
import com.code.page.ibnmsConfig.alarmTemplate.page.TemplateFormPage;

import java.util.Map;

/**
 * Created by Jin on 2014/8/4.
 * 告警配置模板
 */
public class AlarmTemplateListPage extends Page {
    public String title="告警配置模板列表";
    SearchTemplatePage searchTemplate=new SearchTemplatePage();
    AlarmTemplateBtnPage templateBtn=new AlarmTemplateBtnPage();
    TemplateFormPage templateForm=new TemplateFormPage();
    GridPage gridTable=new GridPage();
    public GridPage search(Map<String,String> map)
    {
        gridTable=searchTemplate.search(map);
        return gridTable;
    }
    public GridPage add(Map<String,String> map)
    {
        templateBtn.add();
        templateForm.operate(map);
        return new GridPage();
    }
    public GridPage delete()
    {
        templateBtn.delte();
        return  new GridPage();
    }
    public GridPage operate(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加"))
        {
            templateBtn.add();
            templateForm.operate(map);
            return  gridTable;
        }
        if (operation.equals("修改"))
        {
            gridTable=searchTemplate.search(map);
            gridTable.selectTr(0);
            templateBtn.edit();
            templateForm.operate(map);
            return  gridTable;
        }
        else{
            gridTable=searchTemplate.search(map);
            gridTable.selectTr(0);
            templateBtn.delte();
            return gridTable;
        }
    }
    public void operateTemplateKPI(Map<String,String> map)
    {
        gridTable=searchTemplate.search(map);
        gridTable.selectTr(0);
        templateBtn.edit();
        new TemplateFormPage().operateKPI(map);
        templateBtn.save();
    }
}
