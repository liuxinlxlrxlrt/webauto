package com.webauto.methods;

import com.webauto.base.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Set;

public class MultipleWindowDemo extends Base {

    @Test
    public void test() throws InterruptedException {
//        driver.get("https://www.baidu.com/");
        driver.navigate().to("https://www.baidu.com/");
        //1、打开a页面，在输入框中输入aa
        driver.findElement(By.id("a")).sendKeys("aa");
        String handleA=driver.getWindowHandle();

        //2、打开a页面的超链接，打开b页面
        driver.findElement(By.linkText("b页面")).click();
        driver.findElement(By.id("b")).sendKeys("bb");
        String handleB=driver.getWindowHandle();

        //3、打开a页面的超链接，打开c页面
        driver.findElement(By.linkText("c页面")).click();
        driver.findElement(By.id("c")).sendKeys("cc");
        String handleC=driver.getWindowHandle();

        //4、获取全部窗口的句柄
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (handle.equals(handleA)) {
                continue;
            }
            driver.switchTo().window(handle);
            String windowTitle = driver.getTitle();
            if (windowTitle.equals("b页面")) {
                //5、往b页面,，在输入框中输入bb
                driver.findElement(By.id("b")).sendKeys("bb");
            } else if (windowTitle.equals("c页面")) {
                //6、往c页面,，在输入框中输入cc
                driver.findElement(By.id("c")).sendKeys("cc");
            }
        }
    }
}
