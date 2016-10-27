package com.code.common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import com.code.test.unicom.login.LoginPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Created by jinkai on 2014/6/21.
 */
public class TestCase2 {

    public  WebDriver driver;//=createDriver("IE","http://192.168.1.102:5555/wd/hub");

    public  EventFiringWebDriver eventDriver;//=new EventFiringWebDriver(driver).register(new EventListener());
    public Tools tools;//=new Tools(eventDriver);
    public String[] excelHead;
    public Map<String,String> map;
    public WebDriver 	createDriver(String BrowerType,String nodeURL)
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

            /*如果IE浏览器设置安全性较高，在“Internet Options”中都不要选择“Enable Protected Mode”（保护模式），否则可能遇到如下的错误提示。
            解决方法：
            一种是修改掉IE的设置，不要在任何情况下使用保护模式（protected mode），另一种即是前面代码中如下片段在运行时设置IE的Capabilities。
            第二种方法应该是在运行时设置IE的部分参数。鉴于代码健壮性考虑，使用第二种方法，继续修改代码。
            */
            DesiredCapabilities ieCapabilities = DesiredCapabilities
                    .internetExplorer();
            //ieCapabilities.setCapability("ignoreZoomSetting", true);
            ieCapabilities
                    .setCapability(
                            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                            true);
            //driver = new InternetExplorerDriver(ieCapabilities);
            /*ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability("ie.ensureCleanSession", true);*/
            DesiredCapabilities test=DesiredCapabilities.internetExplorer();
            try {
                if (!nodeURL.equals(""))
                driver = new RemoteWebDriver(new URL(nodeURL),ieCapabilities);
                else
                    driver=new InternetExplorerDriver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        if(BrowerType.equals("FIREFOX"))
        {
            DesiredCapabilities test = DesiredCapabilities.firefox();
            try {
                driver = new RemoteWebDriver(new URL(nodeURL),test);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else
        if(BrowerType.equals("safari")){
            System.out.println("safari浏览器");
           driver=new SafariDriver();
        }
        else
        {
            DesiredCapabilities test=DesiredCapabilities.chrome();
            System.setProperty("webdriver.chrome.driver",  Data.baseDir+ "\\Driver\\chromedriver.exe");
            if (!nodeURL.equals("")) {
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL), test);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else
            driver=new ChromeDriver();
        }
        return driver;
    }


    public EventFiringWebDriver getEventDriver(){return  eventDriver;}

    @Parameters({"Base_URL"})
    //@BeforeClass(alwaysRun=true)
    public void beforeClass(String base_url)
    {
        eventDriver.get(base_url);
        eventDriver.manage().window().maximize();
        LoginPage login=new LoginPage(eventDriver);
        map=new HashMap<String, String>();
        map.put("用户名","jinkai1");
        map.put("密码","1");
        login.login(map);

    }

    public TestCase2(String node)
    {
        driver=createDriver("IE",node);
        eventDriver=new EventFiringWebDriver(driver).register(new EventListener());
        tools=new Tools(eventDriver);
    }

    @AfterClass(alwaysRun=true)
    public  void afterClass()
    {
        //eventDriver.close();
        eventDriver.quit();

    }
    @Parameters({"Base_URL"})
    //@BeforeSuite
    public void beforeSuite()
    {
        /*eventDriver.get(Data.baseUrl);
        eventDriver.manage().window().maximize();
        eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        */
       // driver=createDriver("IE","http://192.168.1.104:5555/wd/hub");
        //eventDriver=new EventFiringWebDriver(driver).register(new EventListener());
        //eventDriver.get(base_url);
        eventDriver.manage().window().maximize();
        eventDriver.get("http://172.21.0.31:8084");
        LoginPage login=new LoginPage(eventDriver);

    }
    //@AfterSuite
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
