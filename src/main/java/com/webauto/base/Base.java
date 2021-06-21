package com.webauto.base;

import com.webauto.utils.UILibraryUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    private Logger logger = Logger.getLogger(Base.class);

    @Parameters(value = "browserType")
    @BeforeSuite
    public void init(String browserType) throws InterruptedException {
        logger.info("******开始测试******");
        logger.info("***配置的浏览器类型为："+browserType);
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
            logger.error("暂不支持，请检查配置");
        }

        //隐式等待是全局，一旦找到这个元素就不会继续等待了
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        //关闭驱动实例，以及所有自动化打开的浏览器窗口
        driver.quit();
        logger.info("******套件执行完毕******");
    }

    /**
     * 输入数据
     *
     * @param pageKeyWord
     * @param uiElementKeyWord
     * @param content
     */
    public void sendKeys(String pageKeyWord, String uiElementKeyWord, String content) {
        logger.info("定位到【"+pageKeyWord+"】的【"+uiElementKeyWord+"】，往里面写入数据【"+content+"】");
        WebElement element = UILibraryUtil.getElementByKeyWord(pageKeyWord, uiElementKeyWord);
        element.sendKeys(content);
    }

    /**
     * 点击元素
     *
     * @param pageKeyWord
     * @param uiElementKeyWord
     */
    public void click(String pageKeyWord, String uiElementKeyWord) {
        logger.info("点击【"+pageKeyWord+"】的【"+uiElementKeyWord+"】");
        WebElement element = UILibraryUtil.getElementByKeyWord(pageKeyWord, uiElementKeyWord);
        element.click();
    }

    /**
     * 访问
     *
     * @param url
     */
    public void to(String url) {
        logger.info("访问页面：【"+url+"】");
        driver.get(url);
    }

    /**
     * 判断某个元素是否存在
     */
    public boolean isElementExist(WebDriver webDriver, By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            System.out.println("不存在此元素");
            return false;
        }
    }
}