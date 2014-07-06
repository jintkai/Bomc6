package com.code.common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.util.Map;

/**
 * Created by jinkai on 2014/6/21.
 */
public class TestCase {
    public Tools tools=new Tools();
    public String[] excelHead;
    public Map<String,String> map;
    public static WebDriver 	createDriver(String BrowerType)
    {
        if(BrowerType=="IE")
        {
            System.setProperty("webdriver.ie.driver",
                    "D:\\eclipse\\selenium\\IEDriverServer.exe");
            DesiredCapabilities ieCapabilities = DesiredCapabilities
                    .internetExplorer();
            ieCapabilities
                    .setCapability(
                            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                            true);
            driver = new InternetExplorerDriver(ieCapabilities);
        }
        else
        if(BrowerType=="FIREFOX")
        {
            driver=new FirefoxDriver();
        }
        else
        {
            System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver driver=createDriver(Data.browserType);
    //public static WebDriver driver;
    public static EventFiringWebDriver eventDriver=new EventFiringWebDriver(driver).register(new EventListener());

    @BeforeMethod
    public void beforeMethod()
    {

    }
    @Parameters({"Base_URL"})
    //@BeforeClass
    public void beforeClass(String base_url)
    {
        //driver=createDriver("IE");
        //eventDriver=new EventFiringWebDriver(driver).register(new EventListener());
        eventDriver.get(base_url);
        eventDriver.manage().window().maximize();
        //eventDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
