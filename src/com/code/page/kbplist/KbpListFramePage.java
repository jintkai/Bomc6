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
    public void init()
    {
       // searchKbp.init();
    }

}