package com.webauto.methods;

import com.webauto.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class ElementOperate extends Base {
    @Test
    public void test(){
        driver.navigate().to("https://www.baidu.com/");

        //1、获取元素的属性
        driver.findElement(By.className("pass-text-input-password")).getAttribute("class");

        //2、获取元素的文本值
        driver.findElement(By.className("pass-text-input-password")).getText();

        //3、获取元素在页面上是否可见
        driver.findElement(By.className("pass-text-input-password")).isDisplayed();

        //4、获取元素在页面上是否可编辑
        driver.findElement(By.className("pass-text-input-password")).isEnabled();

        //5、定位select类型下拉框，并封装成select对象
        Select select =new Select(driver.findElement(By.tagName("xxx")));

        //6、获取select的所有option选项
        List<WebElement> options = select.getOptions();
        for (WebElement webElement : options) {
            if ("男性".equals(webElement.getText())){
                //获取男性这个选项在页面上是否被选中
                System.out.println("判断男性选项是否被选中:"+webElement.isSelected());
            }
        }


        //1、获取所有选项：select.getOptions()

        //2、根据索引选中对应的元素：select.selectByIndex(index)

        //3、选择指定value值对应的选项：select.selectByValue(value)

        //4、选中文本值对应的选项：select.selectByVisibleText(text)
        //sel.selectByVisibleText("最近一月");


    }
}
