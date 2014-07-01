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
    ����table��tr�ĳ���
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
    �����б���ĳһ��td��ֵ������������ò�����td��xpath��eg�� ./td[2]
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
    *�ж�ĳ��td�еķ���ֵ���Ƿ���Ԥ�ڵ�ֵ�൱��������
    *@param xpath��ʾĳ��td��Ҫ��λ��eg ./td[2]��ʾ���ص�td
    * @rowNum ��ʾ��ѯ�����Ӧ�þ��е�������case�п��Դ��룬searchClass�Ա�Ԥ��ֵ��case�д���
     */
    public boolean equalsSearch(String xpath,int rowNum,String searchClass)
    {
        if (getRowNum()==0 && rowNum==0) {
            System.out.println("����ֵ:"+rowNum+",ʵ��ֵ��"+getRowNum());
            return true;
        }
        if (getRowNum()!=rowNum) {
            System.out.println("����ֵ:" + rowNum + ",ʵ��ֵ��" + getRowNum());
            return false;
        }
        String[] rows=getRowsValue(xpath);
        for (int i=0;i<getRowNum();i++)
            if(!rows[i].contains(searchClass))
            {
                System.out.println("������Ŀ��ȷ�����б����ݴ���");
                return false;
            }
        System.out.println("����ֵ:"+rowNum+",ʵ��ֵ��"+getRowNum());
        return true;
    }
}
