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


    public GridTablePage searchKbpByTree(String kbpClass)
    {
        return gridTable=kbpTree.searchKpiByTree(kbpClass);
    }

    public GridTablePage searchByClass(String searchClass){
        return gridTable=searchKbp.searchByClass(searchClass);
    }

    public GridTablePage searchByCaption(String searchCaption){
        return gridTable=searchKbp.searchByCaption(searchCaption);
    }

    public GridTablePage searchByMulti(String searchClass,String searchCaption)
    {

        return gridTable=searchKbp.searchByMulti(searchClass, searchCaption);
    }
    public void init()
    {
        searchKbp.init();
    }

}