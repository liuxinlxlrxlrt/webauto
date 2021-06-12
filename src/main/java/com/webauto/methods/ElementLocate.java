package com.webauto.methods;

import com.webauto.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ElementLocate extends Base {
    @Test
    public void test() throws InterruptedException {
//        driver.get("https://www.baidu.com/");
        driver.navigate().to("https://www.baidu.com/");
        Thread.sleep(3000);

        driver.findElement(By.partialLinkText("登录")).click();

        Thread.sleep(3000);
        /**
         * 1、通过xpath的“绝对路径”定位元素
         */
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div/div/div/div/div/div[3]/p[2]")).click();
        Thread.sleep(3000);

//        WebElement username = driver.findElement(By.className("pass-text-input-userName"));
        /**
         * 2、通过xpath的"相对定位"定位元素
         * //：匹配指定节点; *：匹配任意元素节点;@:选择元素
         * （1）、属性：//*[@id=xxx]
         * （2）、//input[@placeholder='手机/邮箱/用户名']
         * （3）、部分属性匹配：//input[contains(@placeholder,'手机')]
         * （4）、文本值：//*[text()='xxx']
         * （5）、部分文本值：//*[contains(text()='xxx')]
         * （6）、条件组合：//*[contains(text()='xxx')][contains(@placeholder,'手机')]
         */
        WebElement username = driver.findElement(By.xpath("//input[@placeholder='手机/邮箱/用户名']"));
        username.sendKeys("漫步人生路xf");
        Thread.sleep(3000);
        //根据id来定位元素
//        WebElement password = driver.findElement(By.className("pass-text-input-password"));

        WebElement password = driver.findElements(By.cssSelector(".pass-text-input")).get(1);
        password.sendKeys("lx@lx19870613");
        Thread.sleep(3000);
        //点击登录
        driver.findElement(By.className("pass-button-submit")).click();
//        driver.findElement(By.xpath("//*[text()='xxx']"))
//        driver.findElement(By.xpath("//*[contains(text(),'xxx')]")).getTagName());

        //navigate对象
        WebDriver.Navigation navigation = driver.navigate();
        Thread.sleep(2000);
        //访问京东
        navigation.to("https://www.jd.com");
        Thread.sleep(2000);
        //刷新网页
        navigation.refresh();
        Thread.sleep(2000);
        //回退
        navigation.back();
        Thread.sleep(2000);
        //前进
        navigation.forward();
        Thread.sleep(5000);
        driver.quit();
    }
}
