package com.code.page.kbplist;

import com.code.common.GridTablePage;
import com.code.common.Page;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpListFramePage extends Page {

    KbpTreePage kbpTree=new KbpTreePage();
    GridTablePage gridTable=new GridTablePage();

    public void searchKbpByTree()
    {
        gridTable=kbpTree.searchKpiByTree("10-10");
    }


}
