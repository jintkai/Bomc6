package com.code.common;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by jinkai on 2014/6/22.
 */
public class GridTablePage extends Page {
    private int rowNum;
    private int gridCount=0;
    @FindBy(how= How.XPATH,using="//*[@id=\"gridTable\"]")
    WebElement grid;

    List<WebElement> list=null;
    /*
    返回当前页中总行数
     */
    public int getRowNum()
    {
        //List<WebElement> list=grid.findElements(By.xpath(".//tr[@id]"));
        list=tools.findElements(grid,By.xpath(".//tr[@id]"));
        if (list==null)
            rowNum=0;
        else
            rowNum=list.size();
        return  rowNum;
    }
    /*
    返回xpath列的数组
     */
    public String[] getRowsValue(String xpath)
    {
        String[] rowVales=new String[rowNum];
       // List<WebElement> list=tools.findElements(grid,By.xpath(".//tr[@id]"));
        WebElement[] webList = new WebElement[list.size()];
        list.toArray(webList);

        for (int i=0;i<getRowNum();i++) {
            //WebElement row = webList[i].findElement(By.xpath("./td[2]"));
            WebElement row = webList[i].findElement(By.xpath(xpath));
            rowVales[i]=row.getText();
            //System.out.println(rowVales[i]);
        }
        return rowVales;
    }

    /*
    判断数组中的值是否都包含所查询的值
     */
    public boolean equalsSearch(String xpath,int expNum,String searchClass)
    {
        rowNum=getRowNum();
        if (expNum==0 && rowNum==0) {
            System.out.println("列表中行数错误，期望值："+expNum+"，实际值："+rowNum);
            return true;
        }
        if (expNum!=rowNum) {
            System.out.println("列表中行数错误，期望值："+expNum+"，实际值："+rowNum);
            return false;
        }
        String[] rows=getRowsValue(xpath);
        for (int i=0;i<rowNum;i++)
            if(!rows[i].contains(searchClass))
            {
                System.out.println("列表中数据错误，第"+(i+1)+"的值为："+rows[i]+"，期望值是："+searchClass);
                return false;
            }
        System.out.println("期望值："+expNum+"，实际值："+rowNum);
        return true;
    }
}
