package com.code.page.kbplist;

import com.code.common.GridTablePage;
import com.code.common.Page;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpListFramePage extends Page {

    KbpTreePage kbpTree=new KbpTreePage();
    GridTablePage gridTable=new GridTablePage();
    SearchKbpPage searchKbp=new SearchKbpPage();
    KbpFormPage kbpForm=new KbpFormPage();
    KbpBtnPage kbpBtn=new KbpBtnPage();


    public GridTablePage searchKbpByTree(String treeValue)
    {
        return gridTable=kbpTree.searchKpiByTree(treeValue);
    }

    public GridTablePage searchByClass(String searchClass){
        tools.switchToFrame("kbpListFrame");
        return gridTable=searchKbp.searchByClass(searchClass);
    }

    public GridTablePage searchByCaption(String searchCaption){
        tools.switchToFrame("kbpListFrame");
        return gridTable=searchKbp.searchByCaption(searchCaption);
    }

    public GridTablePage searchByMulti(String searchClass,String searchCaption)
    {
        tools.switchToFrame("kbpListFrame");
        return gridTable=searchKbp.searchByMulti(searchClass, searchCaption);
    }
    public int addKbp(String obj[])
    {

        if (!obj[5].equals("0"))
        {
            //通过ifram，tree打开对应的增加窗口
            tools.switchToFrame();
            (new KbpTreePage()).searchKpiByTree(obj[5]);
        }
        else
            tools.switchToFrame("kbpListFrame");
        kbpForm=kbpBtn.openFormByAdd();
        kbpForm.addKBP(obj);
        if (!obj[5].equals("0"))
        return searchKbp.searchByClass(obj[12]).getRowNum();
        return searchKbp.searchByCaption(obj[7]).getRowNum();
    }
    public int delKbp(String obj[])
    {
        tools.switchToFrame("kbpListFrame");
        gridTable=new GridTablePage();
        gridTable.selectGridTable(2);
        int a=1;
        return a;
    }

}