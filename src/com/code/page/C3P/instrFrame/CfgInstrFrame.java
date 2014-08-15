package com.code.page.C3P.instrFrame;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.C3P.instrFrame.page.GroupTreePage;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/23.
 */
public class CfgInstrFrame extends Page {
    public GroupTreePage groupTree = new GroupTreePage();

    public void addGroup(Map<String, String> map) {
        tools.switchToFrame();
        tools.switchToFrame("groupTree");
        groupTree.addGroup(map);
    }
    public void deleteGroup(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("groupTree");
        groupTree.deleteGroup(map);
    }
    public void editGroup(Map<String, String> map)
    {
        tools.switchToFrame("groupTree");
        groupTree.editGroup(map);
    }
    public void searchGroup()
    {
        tools.switchToFrame();
        tools.switchToFrame("groupTree");
        groupTree.searchGroups();
    }
    public void searchByGroup()
    {
        tools.switchToFrame("groupTree");
    }
    public GridPage searchByTree(Map<String,String> map)
    {
        tools.switchToFrame();
        tools.switchToFrame("groupTree");
        groupTree.searchByTree(map);
        tools.switchToFrame();
        tools.switchToFrame("instrList");
        return new GridPage();
    }
    public int searchGroup(String str)
    {
        tools.switchToFrame();
        tools.switchToFrame("groupTree");
        return groupTree.searchGroup(str);
    }

    public ArrayList<Integer> getListOfGroups(String str)
    {
        tools.switchToFrame();
        tools.switchToFrame("groupTree");
        return groupTree.getListOfGroups(str);
    }
    public List<WebElement> findGroups()
    {
        tools.switchToFrame();
        tools.switchToFrame("groupTree");
        return groupTree.findGroups();
    }

}
