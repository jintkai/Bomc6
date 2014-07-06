package com.code.page.kbplist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.kbplist.page.KbpBtnPage;
import com.code.page.kbplist.page.KbpFormPage;
import com.code.page.kbplist.page.SearchKbpPage;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpListPage extends Page {

    SearchKbpPage searchKbp=new SearchKbpPage();
    GridPage gridTable=new GridPage();
    KbpFormPage kbpForm=new KbpFormPage();
    KbpBtnPage kbpBtn=new KbpBtnPage();



    public GridPage getTableByClass(String searchClass){
        return gridTable=searchKbp.searchByClass(searchClass);
    }

    public GridPage getTableByCaption(String searchCaption){
        return gridTable=searchKbp.searchByCaption(searchCaption);
    }

    public GridPage getTableByMulit(String searchClass,String searchCaption)
    {

        return gridTable=searchKbp.searchByMulti(searchClass, searchCaption);
    }


    public int addKbp(String obj[])
    {

        kbpForm=kbpBtn.openFormByAdd();
        //kbpForm.addKBP(obj);
        return searchKbp.searchByCaption(obj[7]).getRowNum();
    }

    public void init()
    {
        searchKbp.init();
    }
    }
