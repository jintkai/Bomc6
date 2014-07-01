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

    /*
    返回table中tr的长度
     */
    public int getRowNum()
    {
        List<WebElement> list=grid.findElements(By.xpath(".//tr[@id]"));
        if (list==null)
            rowNum=0;
        else
            rowNum=list.size();
        return  rowNum;
    }
    /*
    返回列表中某一个td的值，传入参数，该参数是td的xpath。eg： ./td[2]
     */
    public String[] getRowsValue(String xpath)
    {
        String[] rowVales=new String[getRowNum()];
        List<WebElement> list=grid.findElements(By.xpath(".//tr[@id]"));
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
    *判断某个td中的返回值，是否与预期的值相当，参数中
    *@param xpath表示某个td需要定位，eg ./td[2]表示返回的td
    * @rowNum 表示查询结果中应该具有的行数，case中可以传入，searchClass对比预期值，case中传入
     */
    public boolean equalsSearch(String xpath,int rowNum,String searchClass)
    {
        if (getRowNum()==0 && rowNum==0) {
            System.out.println("期望值:"+rowNum+",实际值："+getRowNum());
            return true;
        }
        if (getRowNum()!=rowNum) {
            System.out.println("期望值:" + rowNum + ",实际值：" + getRowNum());
            return false;
        }
        String[] rows=getRowsValue(xpath);
        for (int i=0;i<getRowNum();i++)
            if(!rows[i].contains(searchClass))
            {
                System.out.println("数据条目正确，但列表数据错误！");
                return false;
            }
        System.out.println("期望值:"+rowNum+",实际值："+getRowNum());
        return true;
    }
}
