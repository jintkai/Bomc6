package com.code.page.kbplist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.kbplist.page.KbpBtnPage;
import com.code.page.kbplist.page.KbpFormPage;
import com.code.page.kbplist.page.KbpTreePage;
import com.code.page.kbplist.page.SearchKbpPage;

import java.util.Map;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpListFramePage extends Page {

    KbpTreePage kbpTree=new KbpTreePage();
    //GridTablePage gridTable=new GridTablePage();
    GridPage gridTable=new GridPage();
    SearchKbpPage searchKbp=new SearchKbpPage();
    KbpFormPage kbpForm=new KbpFormPage();
    KbpBtnPage kbpBtn=new KbpBtnPage();


    public GridPage searchKbpByTree(String treeValue)
    {
        return gridTable=kbpTree.searchKpiByTree(treeValue);
    }

    public GridPage searchByClass(String searchClass){
        tools.switchToFrame("kbpListFrame");
        return gridTable=searchKbp.searchByClass(searchClass);
    }

    public GridPage searchByCaption(String searchCaption){
        tools.switchToFrame("kbpListFrame");
        return gridTable=searchKbp.searchByCaption(searchCaption);
    }

    public GridPage searchByMulti(String searchClass,String searchCaption)
    {
        tools.switchToFrame("kbpListFrame");
        return gridTable=searchKbp.searchByMulti(searchClass, searchCaption);
    }
    public GridPage addKbp(Map<String,String> map)
    {
        if (!tools.getMapValue(map,"父节点").equals("0"))
        {
            //通过ifram，tree打开对应的增加窗口
            tools.switchToFrame();
            (new KbpTreePage()).searchKpiByTree(tools.getMapValue(map,"父节点"));
        }
        else
            tools.switchToFrame("kbpListFrame");
        kbpForm=kbpBtn.openFormByAdd();
        kbpForm.addKBP(map);
        if (!tools.getMapValue(map,"父节点").equals("0"))
        return searchKbp.searchByClass(tools.getMapValue(map,"验证"));
        else
            return searchKbp.searchByCaption(tools.getMapValue(map,"KBP名称"));
    }

    public GridPage delKbp(String caption)
    {
        tools.switchToFrame("kbpListFrame");
        gridTable=new GridPage();
        gridTable=searchKbp.searchByCaption(caption);
        gridTable.selectTr(0);
        kbpBtn.deleteForm();
        return searchKbp.searchByCaption(caption);
    }

    public GridPage editKbp(String kbpClass,String caption)
    {
        tools.switchToFrame("kbpListFrame");
        gridTable=new GridPage();
        gridTable=searchKbp.searchByClass(kbpClass);
        gridTable.selectTr(0);
        kbpForm=kbpBtn.editForm();
        caption=caption+tools.getCurrentDateTime();
        kbpForm.editKbp(caption);
        return searchKbp.searchByCaption(caption);
    }
}