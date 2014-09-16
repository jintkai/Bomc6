package com.code.common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinkai on 2014/6/21.
 */
public class TestCase {
    public Tools tools=new Tools();
    public String[] excelHead;
    public Map<String,String> map;
    public static WebDriver 	createDriver(String BrowerType)
    {
        Properties props=System.getProperties();
        String pcVersion=props.getProperty("os.arch");
        System.out.println("操作系统版本："+pcVersion);
        if(BrowerType.equals("IE"))
        {
            if(pcVersion.contains("64"))
            { System.setProperty("webdriver.ie.driver",
                    Data.baseDir+ "\\Driver\\IEDriverServer64.exe");}
            else
                System.setProperty("webdriver.ie.driver",
                        Data.baseDir+ "\\Driver\\IEDriverServer32.exe");


            DesiredCapabilities ieCapabilities = DesiredCapabilities
                    .internetExplorer();
            ieCapabilities
                    .setCapability(
                            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                            true);
            driver = new InternetExplorerDriver(ieCapabilities);
        }
        else
        if(BrowerType.equals("FIREFOX"))
        {
            driver=new FirefoxDriver();
        }
        else
        {
            System.setProperty("webdriver.chrome.driver",  Data.baseDir+ "\\Driver\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver driver=createDriver(Data.browserType);
    //public static WebDriver driver;
    public static EventFiringWebDriver eventDriver=new EventFiringWebDriver(driver).register(new EventListener());


    @Parameters({"Base_URL"})
    //@BeforeClass
    public void beforeClass(String base_url)
    {
        //driver=createDriver("IE");
        //eventDriver=new EventFiringWebDriver(driver).register(new EventListener());
        eventDriver.get(base_url);
        eventDriver.manage().window().maximize();
        //设置隐式等待时间
       // eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //@AfterClass(alwaysRun=true)
    public  void afterClass()
    {
        eventDriver.close();
        eventDriver.quit();

    }
    //@Parameters({"Base_URL"})
    @BeforeSuite
    public void beforeSuite()
    {
        eventDriver.get(Data.baseUrl);
        eventDriver.manage().window().maximize();
        eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterSuite
    public void afterSuite()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eventDriver.close();
        eventDriver.quit();
    }
}
