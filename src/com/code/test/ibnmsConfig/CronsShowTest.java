package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.cronsConf.CronsShowPage;
import com.code.page.ibnmsConfig.cronsConf.domain.CronsFormDomain;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.mustache.Value;

import java.util.List;
import java.util.Map;

/**
 * Created by jon on 2016/10/20.
 */
public class CronsShowTest extends TestCase {
    CronsShowPage cronsShowPage=new CronsShowPage(eventDriver);
    GridPage    gridPage=new GridPage(eventDriver);
    @Parameters({"node"})
    public CronsShowTest(String node) {
        super(node);
    }

    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }



    @Test(priority = 0,description = "增加调度表达式")
    public void addCronsConf()
    {
        CronsFormDomain formDomain=new CronsFormDomain();
        String cronName="selenium"+tools.formatNow();
        formDomain.setCronName(cronName);
        formDomain.setCronDesc("selenium/Desc:"+tools.formatNow());
        formDomain.setSeconds(String.valueOf(tools.random()%60));
        formDomain.setMinute(String.valueOf(tools.random()%60)+","+String.valueOf(tools.random()%60));
        switch(tools.random()%3){
            case 0:formDomain.setHour(String.valueOf(tools.random()%24));
                break;
            case 1:formDomain.setHour("*");
                break;
            case 2:formDomain.setHour(String.valueOf(tools.random()%4)+"-"+String.valueOf(tools.random()%20+4));
                break;
            default:formDomain.setHour(String.valueOf(tools.random()%24)+"/"+String.valueOf(tools.random()%24));
        }

        formDomain.setDay(String.valueOf(tools.random()%31+1));
        formDomain.setMonth(String.valueOf(tools.random()%12+1));
        formDomain.setWeek(String.valueOf(tools.random()%7+1));
        switch(tools.random()%2) {
            case 0:formDomain.setYear(String.valueOf(tools.random() % 7 + 2016));
                break;
            case 1:
                break;
        }
        cronsShowPage.operate("增加",null,formDomain);
        cronsShowPage.search(cronName);
        tools.assertEquals(gridPage.getGridrowNum(),1,formDomain.toString());
    }
    @Test(priority = 2,description = "删除调度表达式")
    public void deleteCronsConf()
    {
        String sql="select * from TB_CFG_SERVICE_CRONINFO where name like '%selenium%'";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        String cronsName=list.get(0).get("name");
        cronsShowPage.operate("删除",cronsName,null);
        cronsShowPage.search(cronsName);
        tools.assertEquals(gridPage.getGridrowNum(),0,cronsName.toString());
    }

    @Test(priority = 1,description = "修改调度表达式")
    public void editCronsConf()
    {
        String sql="select * from TB_CFG_SERVICE_CRONINFO where name like '%selenium%'";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        String cronsName=list.get(0).get("name");

        CronsFormDomain formDomain=new CronsFormDomain();
        formDomain.setCronDesc("selenium/Desc:"+tools.formatNow());
        formDomain.setSeconds(String.valueOf(tools.random()%60));
        formDomain.setMinute(String.valueOf(tools.random()%60)+","+String.valueOf(tools.random()%60));
        switch(tools.random()%3){
            case 0:formDomain.setHour(String.valueOf(tools.random()%24));
                break;
            case 1:formDomain.setHour("*");
                break;
            case 2:formDomain.setHour(String.valueOf(tools.random()%4)+"-"+String.valueOf(tools.random()%20+4));
                break;
            default:formDomain.setHour(String.valueOf(tools.random()%24)+"/"+String.valueOf(tools.random()%24));
        }

        formDomain.setDay(String.valueOf(tools.random()%31+1));
        formDomain.setMonth(String.valueOf(tools.random()%12+1));
        formDomain.setWeek(String.valueOf(tools.random()%7+1));
        switch(tools.random()%2) {
            case 0:formDomain.setYear(String.valueOf(tools.random() % 7 + 2016));
                break;
            case 1:
                break;
        }

        cronsShowPage.operate("修改",cronsName,formDomain);
        cronsShowPage.search(cronsName);
        tools.assertEquals(gridPage.getGridrowNum(),1,cronsName.toString());
    }

}
