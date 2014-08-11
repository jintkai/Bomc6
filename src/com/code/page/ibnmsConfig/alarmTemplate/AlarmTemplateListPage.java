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
    public GridPage search(Map<String,String> map)
    {
        return  searchTemplate.search(map);
    }
    public GridPage add(Map<String,String> map)
    {
        templateBtn.add();
        templateForm.add(map);
        return new GridPage();
    }
    public GridPage delete()
    {
        templateBtn.delte();
        return  new GridPage();
    }
}
