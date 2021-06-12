package com.webauto.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class Base {

    public static WebDriver driver;

    @Parameters(value = "browserType")
    @BeforeSuite
    public void init(String browserType) throws InterruptedException {
        if ("ie".equalsIgnoreCase(browserType)) {
            //设置系统变量，指定驱动文件路径
            System.setProperty("webdriver.ie.driver", "D:\\Selenium+appium\\IEDriverServer.exe");
            //忽略保护模式
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //启动浏览器
            driver = new InternetExplorerDriver(desiredCapabilities);

        } else if ("firefox".equalsIgnoreCase(browserType)) {

            System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
            driver = new FirefoxDriver();

        } else if ("chrome".equalsIgnoreCase(browserType)) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            System.out.println("暂不支持，请检查配置");
        }
    }

//    @AfterSuite
//    public void tearDown(){
//       driver.quit();
//    }
}
