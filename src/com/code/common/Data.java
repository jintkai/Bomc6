package com.code.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 02/07/2014.
 */
public interface Data {
    public static String browserType = "IE";
    public static String baseDir = ".";
    public static String baseUrl = "http://172.21.0.31:8084";
    /*
    KbpList，KBP列表对应的tableXpath，td，tr。
    gridXpath表示data区域的数据table，
    headXpath表示表头区域的数据table；
    headThXpaht表示表头的th，在headXpath中定位
    dataTableTrXpath表示数据区域tr的xpath。
    dataTableTdXpath表示数据区域td的xpath，在dataTableTrXpath下；
     */
    public static String gridXpath = ".//*[@id=\"gridTable\"]";
    public static String headXpath = ".//*[@id=\"gview_gridTable\"]/div[2]/div/table";
    public static String headThXpath = ".//th[@id]";
    public static String dataTableTrXpath = ".//tr[@id]";
    public static String dataTableTdXpath = ".//td";
    public static String selectBtId = "cb_gridTable";

    /*
    标准的增加、删除、修改、选择等控件
     */
    public static String btnAdd = "btn-add";
    public static String btnEdit = "btn-edit";
    public static String btnDel = "btn-del";
    public static String btnSelect="btn-select";
    /*
    KpiPage的WebElement
     */
    public static String kpiIdID = "kpi.kpiId";
    public static String kpiNameID = "kpi.kpiName";
    public static String kbpClassID = "kpi.kbpClass";
    public static String kpiTypeID = "kpi.kpiType";
    public static String kpiStylestringID = "kpi.kpiStylestring";
    public static String kpiStylenumberID = "kpi.kpiStylenumber";
    public static String kpiStyledatetimeID = "kpi.kpiStyledatetime";
    public static String kpiMeasureID = "kpi.kpiMeasure";
    public static String kpiDescID = "kpi.kpiDesc";
    public static String trendFlagName = "kpi.trendFlag";
    public static String baseLineFlagName = "kpi.baseLineFlag";
    public static String kpiMngTypeXpath = "";
    /*
    KBPPage定位WebElement
     */
    public static String classAfterID = "classAfter";
    public static String kbpCaptionID = "kbp.kbpCaption";
    public static String kbpDescName = "kbp.kbpDesc";
    public static String middleID = "kbp.middle";
    public static String viewStyleID = "kbp_viewStyle";
    public static String enableID = "kbp_enable";
}