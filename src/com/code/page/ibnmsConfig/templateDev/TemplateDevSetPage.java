package com.code.page.ibnmsConfig.templateDev;

import com.code.common.BtnPage;
import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.templateDev.page.TemplateDevListSearchPage;

import java.util.Map;

/**
 * Created by Jin on 2014/8/21.
 * 资源与模板关联设置页面
*/

public class TemplateDevSetPage extends Page {
    public String title="资源与模版关联设置";
    GridPage gridTable=new GridPage();
    public BtnPage btn=new BtnPage();
    public TemplateDevListSearchPage devSearch=new TemplateDevListSearchPage();
    public GridPage search(Map<String,String> map)
    {
        devSearch.search(map);
        return gridTable;
    }
}
