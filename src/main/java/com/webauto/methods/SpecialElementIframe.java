package com.webauto.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SpecialElementIframe {
    private static ChromeDriver chromeDriver;

    public static void main(String[] args) throws InterruptedException {

        /**
         * 1、单层iframe嵌套
         */
        String url = "https://www.baidu.com/";
        openChrom(url);

        //四种切换
//        chromeDriver.switchTo().frame(index);
//        chromeDriver.switchTo().frame(id);
//        chromeDriver.switchTo().frame(name);
//        chromeDriver.switchTo().frame(WebElement);

        //# 跳到最外层页面（否则找不到元素）
        chromeDriver.switchTo().defaultContent();

        /**
         *2、多层iframe嵌套
         */
//        String url = "https://www.baidu.com/";
        chromeDriver.get(url);
        Thread.sleep(2000);
        //1、切换到a.html的iframe
        chromeDriver.switchTo().frame("aiframe");
        //在a.html的iframe中输入“a1”
        chromeDriver.findElement(By.id("aa")).sendKeys("a1");
        Thread.sleep(2000);

        //2.切换到b.html的iframe
        chromeDriver.switchTo().frame("biframe");
        //在b.html的iframe中输入“b1”
        chromeDriver.findElement(By.id("bb")).sendKeys("b1");
        Thread.sleep(2000);

        //3、切换到c.html的iframe
        chromeDriver.switchTo().frame("ciframe");
        //在c.html的iframe中输入“c1”
        chromeDriver.findElement(By.id("cc")).sendKeys("c1");

        //4、返回到b.html的iframe中输入“b2”
        chromeDriver.switchTo().parentFrame();//# 跳出当前表单,跳到上一级窗口
        chromeDriver.findElement(By.id("bb")).sendKeys("b2");


        //5、返回到a.html的iframe中输入“a2”,
        chromeDriver.switchTo().parentFrame();//# 跳出当前表单,跳到上一级窗口
        chromeDriver.findElement(By.id("aa")).sendKeys("a2");

        //6、直接从a.html的iframe中切换到c.html的iframe，是不可以的，必须一级一级进入

        //7、如果iframe没有任何属性，通过索引index进入iframe
        chromeDriver.switchTo().frame(0);
        chromeDriver.findElement(By.id("bbb")).sendKeys("b3");

        //8、通过整个iframe元素定位
        WebElement iframeElement = chromeDriver.findElement(By.tagName("iframe"));
        chromeDriver.switchTo().frame(iframeElement);
        chromeDriver.findElement(By.id("bbbb")).sendKeys("b4");

    }

    public static void openChrom(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        chromeDriver = new ChromeDriver();

        chromeDriver.get(url);
    }
}
