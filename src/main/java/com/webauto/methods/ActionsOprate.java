package com.webauto.methods;

import com.webauto.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 *鼠标、键盘事件
 */
public class ActionsOprate extends Base {
    @Test
    public void test(){
        /**
         * 1、拖拽节点基本操作
         */
        driver.navigate().to("https://www.baidu.com/");
        WebElement source = driver.findElement(By.id("source"));

        WebElement target = driver.findElement(By.id("target"));

      Actions actions= new Actions(driver);
      actions.dragAndDrop(source,target).build().perform();


        /**
         * 鼠标移动并悬浮在元素上
         */
        // 设置
        WebElement settings = driver.findElement(By.xpath("//*[@id='u1']/a[8]"));

        Actions action = new Actions(driver);
        action.moveToElement(settings).perform();
    }
}
