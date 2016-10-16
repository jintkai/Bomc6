

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Locator {
    private String yamlfile;
    private WebDriver driver;
    private Map<String, Map<String, String>> ml;
    public Locator(){

    }
    public Locator(WebDriver driver) {
        yamlfile = "demo";
        this.getYamlFile();
        this.driver = driver;
    }

    public void getYamlFile() {
        File f = new File("locator/" + yamlfile + ".yaml");
        try {
            ml = Yaml.loadType(new FileInputStream(f.getAbsolutePath()),
                    HashMap.class);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private By getBy(String type, String value) {
        By by = null;
        if (type.equals("id")) {
            by = By.id(value);
        }
        if (type.equals("xpath")) {
            by = By.xpath(value);
        }
        return by;
    }

    public WebElement getElement(String key) {
        // String type = ml.get(key).get("type");
        // String value = ml.get(key).get("value");
        // // return driver.findElement(this.getBy(type, value));
        // // return this.waitForElement(this.getBy(type, value));
        // WebElement element;
        // element = this.waitForElement(this.getBy(type, value));
        // if (!this.waitElementToBeDisplayed(element))
        // element = null;
        // return element;
        // return this.getLocator(key, true);
        return this.getLocator(key, null, true);
    }

    public WebElement getElement(String key, String[] replace) {

        return this.getLocator(key, replace, false);
    }

    private boolean waitElementToBeDisplayed(final WebElement element) {
        boolean wait = false;
        if (element == null)
            return wait;
        try {
            wait = new WebDriverWait(driver, 10)
                    .until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return element.isDisplayed();
                        }
                    });
        } catch (Exception e) {
            System.out.println(element.toString() + " is not displayed");
        }
        return wait;
    }

    private boolean waitElementToBeNonDisplayed(final WebElement element) {
        boolean wait = false;
        if (element == null)
            return wait;
        try {
            wait = new WebDriverWait(driver,10)
                    .until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return !element.isDisplayed();
                        }
                    });
        } catch (Exception e) {
            System.out.println(element.toString() + " is also displayed");
        }
        return wait;
    }

    private WebElement waitForElement(final By by) {
        WebElement element = null;
        int waitTime = 10;
        try {
            element = new WebDriverWait(driver, waitTime)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return d.findElement(by);
                        }
                    });
        } catch (Exception e) {
            System.out.println(by.toString() + " is not exist until "
                    + waitTime);
        }
        return element;
    }

    public WebElement getElementNoWait(String key) {
        // WebElement element;
        // String type = ml.get(key).get("type");
        // String value = ml.get(key).get("value");
        // try {
        // element = driver.findElement(this.getBy(type, value));
        // } catch (Exception e) {
        // element = null;
        // }
        // return element;
        // return this.getLocator(key, false);
        return this.getLocator(key, null, false);
    }

    // 多态形式，字符串替换
    public WebElement getElementNoWait(String key, String[] replace) {

        return this.getLocator(key, replace, false);
    }

    // 由于getElement和getElementNoWait非常相似，所以重构一下这两个方法
    private WebElement getLocator(String key, String[] replace, boolean wait) {
        WebElement element = null;
        if (ml.containsKey(key)) {
            Map<String, String> m = ml.get(key);
            String type = m.get("type");
            String value = m.get("value");
            if (replace != null) {
                value = this.getLocatorString(value, replace);
            }
            By by = this.getBy(type, value);
            if (wait) {
                element = this.waitForElement(by);
                boolean flag = this.waitElementToBeDisplayed(element);
                if (!flag)
                    element = null;
            } else {
                try {
                    element = driver.findElement(by);
                } catch (Exception e) {
                    element = null;
                }
            }
        } else {
            System.out.println("Locator " + key + " is not exists in "
                    + yamlfile + ".yaml");
        }
        return element;
    }

    private String getLocatorString(String locatorString, String[] ss) {
        for (String s : ss) {
            locatorString = locatorString.replaceFirst("%s", s);
        }
        return locatorString;
    }



    public void test() {
        // TODO Auto-generated method stub
        //SeleniumDriver selenium = new SeleniumDriver();
        //WebDriver driver=new SafariDriver();
        WebDriver driver=new FirefoxDriver();

          //EventFiringWebDriver eventDriver;//=new EventFiringWebDriver(driver).register(new EventListener());

        Locator l = new Locator(driver);
        driver.navigate().to("http://www.baidu.com");
        WebElement element;
        element = l.getElement("baidu_input1");
        // String[] replace = new String[] { "kw" };// element = l.getElement("baidu_input", replace);
        element.sendKeys("aa");
    }

    @Test
    public void test2()
    {

    }

}