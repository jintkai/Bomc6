package com.code.page.ibnmsConfig.kbplist;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.kbplist.page.KbpBtnPage;
import com.code.page.ibnmsConfig.kbplist.page.KbpFormPage;
import com.code.page.ibnmsConfig.kbplist.page.KbpTreePage;
import com.code.page.ibnmsConfig.kbplist.page.SearchKbpPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 01/07/2014.
 * Kbp的Frame页面；
 */
public class KbpListFramePage extends Page {

    private KbpTreePage kbpTree=new KbpTreePage();
    GridPage gridTable=new GridPage();
    public SearchKbpPage searchKbp=new SearchKbpPage();
    KbpFormPage kbpForm=new KbpFormPage();
    public KbpBtnPage kbpBtn=new KbpBtnPage();
    public String title="Kbp列表";
    public String kbpTreeIFrame="kbpTree";
    public String kbpListIFrame="kbpListFrame";



    public GridPage search(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(kbpListIFrame);
        return this.searchKbp.search(map);
    }
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame(kbpTreeIFrame);
        kbpTree.searchByTree(tools.getMapValue(map, "KBP"));
        tools.switchToFrame();
        tools.switchToFrame(kbpListIFrame);
        return new GridPage();
    }
    public GridPage operateKbp(Map<String,String> map) {
        String operation = tools.getMapValue(map, "操作类型");
        if (operation.contains("增加")) {
            tools.switchToFrame();
            tools.switchToFrame(kbpTreeIFrame);
            kbpTree.searchByTree(tools.getMapValue(map, "KBP编号前缀"));
            tools.switchToFrame();
            tools.switchToFrame(kbpListIFrame);
            kbpBtn.add();
            kbpForm = new KbpFormPage();
            return kbpForm.operateKbp(map);
        }
        if (operation.contains("修改")) {
            tools.switchToFrame();
            tools.switchToFrame(kbpListIFrame);
            gridTable = searchKbp.search(map);
            gridTable.selectTr(0);
            //gridTable.selectTrs(gridTable.getListOftr("KBP名称", tools.getMapValue(map, "选择名称")));
            kbpBtn.edit();
            kbpForm = new KbpFormPage();
            return kbpForm.operateKbp(map);
        } else if (operation.contains("删除")) {
            tools.switchToFrame();
            tools.switchToFrame(kbpListIFrame);
            gridTable = searchKbp.search(map);
            gridTable.selectTr(0);
            //gridTable.selectTrs(gridTable.getListOftr("KBP名称", tools.getMapValue(map, "选择名称")));
            kbpBtn.delete();
            return gridTable;
        }
        return gridTable;
    }
}