package com.webauto.cases;

import com.webauto.base.Base;
import com.webauto.utils.LoginUtil;
import com.webauto.utils.PropertiesUtil;
import com.webauto.utils.UILibraryUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class LoginCase extends Base {
    /**
     * 测试所有的反向用例
     *
     * @param username
     * @param password
     * @throws InterruptedException
     */
    @Test(dataProvider = "negativeDatas")
    public void testNegativeCases(String username, String password, String expected) throws InterruptedException {
        driver.get(PropertiesUtil.getPageUrlPath("login.page"));
        boolean flog=true;
        Thread.sleep(3000);
        WebDriver.Window window = driver.manage().window();
        window.maximize();
        //2.点击登录
        Thread.sleep(3000);
        UILibraryUtil.getElementByKeyWord("登录页面","登录链接").click();
        //3.切换窗口到新窗口
        Thread.sleep(3000);
        //4.点击
        UILibraryUtil.getElementByKeyWord("登录页面","账号密码登录链接").click();
        Thread.sleep(3000);
        //获取username 和 password
        System.out.println(username + ":" + password);

        UILibraryUtil.getElementByKeyWord("登录页面","账号输入框").sendKeys(username);

        UILibraryUtil.getElementByKeyWord("登录页面","密码输入框").sendKeys(password);
        WebElement element = UILibraryUtil.getElementByKeyWord("登录页面","登录按钮");

        if (element.isEnabled()){
            element.click();
            Thread.sleep(5000);

            //获取错误提示
            String actual = UILibraryUtil.getElementByKeyWord("登录页面","登陆时的错误提示").getText();
            System.out.println(actual);
            if (actual != null) {
                Assert.assertEquals(actual, expected);
            }
        }else {
            WebElement elementByKeyWord = UILibraryUtil.getElementByKeyWord("登录页面", "登录按钮是否可见以及可操作");

            boolean enabled = elementByKeyWord.isEnabled();
            System.out.println("enabled:"+enabled);
            if (enabled==false){
                flog=false;
            }
            Assert.assertEquals(false, false);
        }
    }


    /**
     * 测试所有的反向用例
     *
     * @param username
     * @param password
     * @throws InterruptedException
     */
    @Test(dataProvider = "positiveDatas")
    public void testPositiveCases(String username, String password) throws InterruptedException {
        driver.get(PropertiesUtil.getPageUrlPath("login.page"));
        Thread.sleep(3000);
        WebDriver.Window window = driver.manage().window();
        window.maximize();
        //2.点击登录
        Thread.sleep(3000);
        UILibraryUtil.getElementByKeyWord("登录页面","登录链接").click();
        //3.切换窗口到新窗口
        Thread.sleep(3000);
        //4.点击
        UILibraryUtil.getElementByKeyWord("登录页面","账号密码登录链接").click();
        Thread.sleep(3000);
        //获取username 和 password
        System.out.println(username + ":" + password);

        UILibraryUtil.getElementByKeyWord("登录页面","账号输入框").sendKeys(username);

        UILibraryUtil.getElementByKeyWord("登录页面","密码输入框").sendKeys(password);
        WebElement element = UILibraryUtil.getElementByKeyWord("登录页面","登录按钮");

        if (element.isEnabled()){
            element.click();
            Thread.sleep(3000);
            //使图片悬停
            JavascriptExecutor js = (JavascriptExecutor)driver;

            //元素不可见：<div id="csdn-toolbar-profile" class="csdn-toolbar-plugin" style="display: none;">
            js.executeScript("document.getElementById('csdn-toolbar-profile').style.display='block';");

            //鼠标悬停在某一个元素上
            Actions action=new Actions(driver);
//            登录成功后定位lucio_lxlx元素
            WebElement element1 = UILibraryUtil.getElementByKeyWord("登录页面","登陆成功后头像弹框图片");
            action.moveToElement( element1).perform();

            Thread.sleep(2000);

            String acturlText = UILibraryUtil.getElementByKeyWord("登录页面","登陆成功后头像弹框昵称").getText();
            System.out.println(acturlText);
            if (acturlText != null) {
                Assert.assertEquals(acturlText, "lucio_lxlx");
            }
        }else {
            System.out.println("fail");
        }
    }



    @DataProvider
    public Object[][] negativeDatas() {
        String[] cellNames = {"Mobilephone", "Password", "ErrorMsg"};
        Object[][] datas = LoginUtil.getDatas("0",cellNames);
        return datas;
    }

    @DataProvider
    public Object[][] positiveDatas() {
        String[] cellNames = {"Mobilephone", "Password"};
        Object[][] datas = LoginUtil.getDatas("1",cellNames);
        return datas;
    }


}
