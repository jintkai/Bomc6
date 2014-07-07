package com.code.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import javax.xml.crypto.*;
import java.util.List;

/**
 * Created by jinkai on 05/07/2014.
 */
public class GridPage  extends Page implements Data{
    Tools tools=new Tools();
    private int rowNum;
    private int gridCount=0;
    int tdIndex=0;
    @FindBy(id=selectBtId)
    WebElement selectBt;
    /*
    table数据区域
     */
    @FindBy(xpath = gridXpath)
    WebElement grid;

    /*
    表头xpath
     */
    @FindBy(how=How.XPATH,using=headXpath)
    WebElement headtable;
    /*
    Table数据区域中，tr的数组，tr表示列表中的数据
     */
    List<WebElement> dataList=null;


    /*
返回当前页中总行数
*/
    public int getRowNum()
    {
        dataList=tools.findElements(grid,By.xpath(dataTableTrXpath));
        if (dataList==null)
            rowNum=0;
        else
            rowNum=dataList.size();
        return  rowNum;
    }

    /*
    返回table的表头数组
     */
    public String[] getHead()
    {

        //System.out.println(headtable.getTagName());
        List<WebElement> list=tools.findElements(headtable, By.xpath(this.headThXpath));
        //System.out.println(list.size());
        WebElement[] eles = new WebElement[list.size()];
        list.toArray(eles);
        String str[]=new String[list.size()];
        for(int i=0;i<list.size();i++)
        {
            str[i]=eles[i].getText().trim();
            //System.out.println(str[i]);
        }
        return str;
    }
    /*
    根据参数，返回参数在数据中的下标值+1
     */
    public int HeadIndex(String str)
    {
        tdIndex=0;
        /*if(str=="")
        {
            System.out.println("所查询的Table列名错误,列名不能为空");
            return tdIndex;
        }*/
        String head[]=this.getHead();
        for (int i=0;i<head.length;i++)
        {
            if (head[i].equals(str))
            {
                tdIndex=i+1;
                break;
            }
        }
        if (tdIndex==0)
        {
            System.out.println("所查询的Table列名错误，列名（"+str+"）不在列表中");
        }
        return tdIndex;
    }

    /*
    根据输入的行数，列名，返回table中指定行数的指定列名
     */
    public String getTdOfTr(int index,String str)
    {
        return getTdOfAllTr(str)[index-1];
    }

    /*
    返回数据区域，某一列的所有数据，组成的数组
     */
    public String[] getTdOfAllTr(String str)
    {
        tdIndex=this.HeadIndex(str);
        String[] rowVales=new String[getRowNum()];
         for (int i=0;i<rowNum;i++) {
             String trXpath=this.dataTableTrXpath+"["+(i+1)+"]";
             String tdXpath=this.dataTableTdXpath+"["+tdIndex+"]";
             WebElement webTr=tools.findBy(grid,By.xpath(trXpath));
             WebElement webTd=tools.findBy(webTr,By.xpath(tdXpath));
            rowVales[i]=webTd.getText().trim();
        }
        return rowVales;
    }

    public void selectTr(int index)
    {
        if (index==0)
        {
            System.out.println("选择所有");
            tools.click(selectBt);
        }
    }

    /*
    判断数组中的值是否都包含所查询的值
     */
    public boolean equalsSearch(String colStr,int expNum,String searchClass)
    {
        rowNum=getRowNum();
        if (expNum==0 && rowNum==0) {
            System.out.println("期望值："+expNum+"，实际值："+rowNum);
            return true;
        }
        if (expNum!=rowNum) {
            System.out.println("定位，列表中行数错误，期望值："+expNum+"，实际值："+rowNum);
            return false;
        }
        String[] rows=getTdOfAllTr(colStr);
        for (int i=0;i<rowNum;i++)
            if(!rows[i].contains(searchClass))
            {
                System.out.println("通过列名："+colStr+"定位，列表中行数错误，期望值："+searchClass+"，实际值："+rows[i]);
                return false;
            }
        System.out.println("期望值："+expNum+"，实际值："+rowNum);
        return true;
    }
}
