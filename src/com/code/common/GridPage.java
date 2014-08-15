package com.code.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import javax.xml.crypto.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jinkai on 05/07/2014.
 * 对列表Table的处理
 */
public class GridPage  extends Page implements Data{
    Tools tools=new Tools();
    private int rowNum;
    private int gridCount=0;
    int tdIndex=0;
    private WebDriverWait wait;
    @FindBy(id=selectBtId)
    public WebElement selectBt;
    //读取中....
    @FindBy(id="load_gridTable")
    WebElement loadGrid;
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

    //int gridTable_cd=3;
    int gridTable_cd=1;
    String gridTable_cbID="gridTable_cb";
    public void setWait()
    {
        wait=new WebDriverWait(tools.getDriver(),10,500);
    }
    public void loadGridUnDisplay()
    {
        setWait();
        Boolean isdisplay=wait.until(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        //System.out.println("GridPage>>>loadGridUnDisplay>>>>>"+tools.getCurrentDateTime());
                        return tools.getAttribute(loadGrid,"style").toLowerCase().contains("display: none");
                    }
                }
        );

    }
    /**
     * 返回当前页面中总行数；
     * @return
     */
    public int getRowNum()
    {
        loadGridUnDisplay();
        dataList=tools.findElements(grid,By.xpath(dataTableTrXpath));
        if (dataList==null)
            rowNum=0;
        else
            rowNum=dataList.size();
        return  rowNum;
    }

    /**
     * 返回table中的表头数组
     * @return
     */
    public String[] getHead()
    {
        List<WebElement> list=tools.findElements(headtable, By.xpath(this.headThXpath));
        WebElement[] eles = new WebElement[list.size()];
        list.toArray(eles);
        String str[]=new String[list.size()];
        for(int i=0;i<list.size();i++)
        {
            str[i]=eles[i].getText().trim();
            if (tools.getAttribute(eles[i],"id").contains("gridTable_cb"))
            {
                gridTable_cd=i+1;
                System.out.println(gridTable_cbID);
            }
        }
        return str;
    }

    /**
     * 返回列名在表头中的列下标
     * @param str 列名
     * @return 列表str在表头中的下标，0表示该列没有在表头中
     */
    public int HeadIndex(String str)
    {
        tdIndex=0;
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
        loadGridUnDisplay();
        return getTdOfAllTr(str)[index-1];
    }

    /*
    返回数据区域，某一列的所有数据，组成的数组
     */

    /**
     * 返回当前页中，某列组成的数据对象
     * @param str 列名
     * @return 返回某列组成的数组；
     */
    public String[] getTdOfAllTr(String str)
    {
        loadGridUnDisplay();
        System.out.println("开始执行headIndex函数"+tools.getCurrentDateTime());
        tdIndex=this.HeadIndex(str);
        System.out.println("结束执行headIndex函数"+tools.getCurrentDateTime());
        String[] rowVales=new String[getRowNum()];
         for (int i=0;i<rowNum;i++) {
             String trXpath=this.dataTableTrXpath+"["+(i+1)+"]";
             String tdXpath=this.dataTableTdXpath+"["+tdIndex+"]";
             WebElement webTr=tools.findBy(grid,By.xpath(trXpath));
             WebElement webTd=tools.findBy(webTr,By.xpath(tdXpath));
             System.out.println(trXpath+tdXpath);
            // WebElement webTd=tools.findBy(grid,By.xpath(trXpath+tdXpath));
            rowVales[i]=webTd.getText().trim();
        }
        System.out.println("结束执行headIndex的for循环函数"+tools.getCurrentDateTime());
        return rowVales;
    }
    /**
     * 返回当前页中，某列组成的数据对象
     * @param td 列号
     * @param tr 行号,0表示第一行数据，从0开始
     * @return 返回某列某行的WebElement元素td；
     */
    public WebElement getTdEleOfTr(int td,int tr)
    {
        loadGridUnDisplay();
        String trXpath=this.dataTableTrXpath+"["+(tr+1)+"]";
        String tdXpath=this.dataTableTdXpath+"["+td+"]";
        WebElement webTr=tools.findBy(grid,By.xpath(trXpath));
        WebElement webTd=tools.findBy(webTr,By.xpath(tdXpath));
        return webTd;
    }

    /**
     * 根据输入行号，返回当前页面中，该行的所有列元素组成的Map对象；
     * @param index 行号
     * @return
     */
    public Map<String,String> getTrOfAllTd(int index)
    {
        loadGridUnDisplay();
        String heads[]=getHead();
        String trXpath=this.dataTableTrXpath+"["+index+"]";
        WebElement tr=tools.findBy(grid,By.xpath(trXpath));
        List<WebElement> tdList=tools.findElements(tr,By.xpath(dataTableTdXpath));
        WebElement tds[]=new WebElement[tdList.size()];
        tdList.toArray(tds);
        String str[]=new String[tds.length];
        for (int i=0;i<tds.length;i++) {
            str[i]=tds[i].getText().trim();
        }
        return tools.changeStringToMap(heads,str);
    }

    /**
     * 根据输入的列名，列值，查询当前页中，该列，列值与输入值相等的行下标；
     * @param colName 列名
     * @param colStr 列值
     * @return 返回相等的行下标
     */
    public ArrayList<Integer> getListOftr(String colName,String colStr)
    {
        loadGridUnDisplay();
        ArrayList<Integer> list = new ArrayList();
        String rowValues[]=getTdOfAllTr(colName);
        for (int i=0;i<rowValues.length;i++)
        {
           if( rowValues[i].contains(colStr))
            list.add(i+1);
        }
        return list;
    }

    /**
     * 根据行下标，选择当前页的行
     * @param index 行号，需要选择的行号，0表示所有行；
     */
    public void selectTr(int index)
    {

        if (index==0)
        {
            System.out.println("选择所有");
            tools.click(selectBt);
        }
        else
        {
            System.out.println("选择部分");
            String trXpath=this.dataTableTrXpath+"["+(index)+"]";

            String tdXpath=this.dataTableTdXpath+"["+gridTable_cd+"]";
            System.out.println(trXpath);
            System.out.println(tdXpath);
            WebElement webTr=tools.findBy(grid,By.xpath(trXpath));
            WebElement webTd=tools.findBy(webTr,By.xpath(tdXpath));
            tools.findBy(webTd,By.xpath("./input")).click();
            System.out.println(tools.findBy(webTd,By.xpath("./input")).isSelected());
        }
    }
    public void selectAllTr()
    {
        tools.click(selectBt);
    }
    /**
     * 根据行下标，选择当前页的行
     * @param list 保存行号的数组；
     */
    public int selectTrs(ArrayList<Integer> list)
    {
        loadGridUnDisplay();
        if (list.size()==0)
        {Reporter.log("请选择数据");
            return 0;}
        for (int i=0;i<list.size();i++) {
            System.out.println("选择部分");
            String trXpath = this.dataTableTrXpath + "[" + list.get(i) + "]";
            String tdXpath = this.dataTableTdXpath + "[" + gridTable_cd + "]";
            WebElement webTr = tools.findBy(grid, By.xpath(trXpath));
            WebElement webTd = tools.findBy(webTr, By.xpath(tdXpath));
            WebElement selectTd=tools.findBy(webTd, By.xpath("./input"));
            tools.click(selectTd);
        }
        return list.size();
    }

    /*
    判断数组中的值是否都包含所查询的值
     */

    /**
     * 查询当前页中所有数据，某列的数据是否包含预期的数据，且页面的总行数是否等于预期值
     * @param colStr 列名
     * @param expNum 预期行数
     * @param searchClass 列的预期值
     * @return 若总数相等且每行都包含，则返回true
     */
    public boolean equalsSearch(String colStr,int expNum,String searchClass)
    {
        loadGridUnDisplay();
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
    public WebElement getWebElementTr(int index)
    {
        loadGridUnDisplay();
        WebElement element;
        //grid.findElement(By.cssSelector("tr[id="+index+"]"));
        return grid.findElement(By.cssSelector("tr[id='"+index+"']"));

    }

}
