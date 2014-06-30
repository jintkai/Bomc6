package com.code.page.kbplist;

import com.code.common.Page;
import com.code.common.TestCase;
import com.code.common.GridTablePage;

/**
 * Created by jinkai on 2014/6/21.
 */
public class KbpListFramePage extends Page {
    public KbpTreePage kbpTree=new KbpTreePage();
    public GridTablePage gridTable=new GridTablePage();
    public void switchToTree()
    {
        TestCase.driver.switchTo().frame(0);
    }
    public void seitchToList()
    {

    }

    public KbpListPage kbpListPage=new KbpListPage();

}
