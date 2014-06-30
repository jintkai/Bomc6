package com.code.page.kbplist;

import com.code.common.GridTablePage;
import com.code.common.Page;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpListPage extends Page {

    SearchKbpPage searchKbp=new SearchKbpPage();
    GridTablePage gridTable=new GridTablePage();
    KbpFormPage kbpForm=new KbpFormPage();
    KbpBtnPage kbpBtn=new KbpBtnPage();



    public GridTablePage getTableByClass(String searchClass){
        return gridTable=searchKbp.searchByClass(searchClass);
    }

    public GridTablePage getTableByCaption(String searchCaption){
        return gridTable=searchKbp.searchByCaption(searchCaption);
    }

    public GridTablePage getTableByMulit(String searchClass,String searchCaption)
    {

        return gridTable=searchKbp.searchMulti(searchClass,searchCaption);
    }

    public int addKbp(String obj[])
    {
        kbpForm= (KbpFormPage) kbpBtn.getAddForm();
        kbpForm.addKBP(obj);
        return searchKbp.searchByCaption(obj[7]).getRowNum();
    }
    public void init()
    {
        searchKbp.init();
    }
    }
