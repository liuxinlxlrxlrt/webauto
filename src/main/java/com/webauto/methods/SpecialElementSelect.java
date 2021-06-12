package com.webauto.methods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class SpecialElementSelect {
    private static ChromeDriver chromeDriver;

    public static void main(String[] args) throws InterruptedException {

        String url = "https://www.baidu.com/";
        openChrom(url);
        //select下拉框
        //设置元素：<span class="s-top-right-text c-font-normal c-color-t"
        // id="s-usersetting-top" name="tj_settingicon">设置</span>

        //悬停
        WebElement element=chromeDriver.findElement(By.id("s-usersetting-top"));
        Thread.sleep(3000);
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(element).perform();
        Thread.sleep(3000);
        //高级搜索：/html/body/div[1]/div[1]/div[4]/div[1]/div/a[2]
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[4]/div[1]/div/a[2]")).click();
        Thread.sleep(2000);
        //切换到浮窗
        Alert alert = chromeDriver.switchTo().alert();
        alert.accept();

        /**
         * 2、select选项下来框
         */
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div[6]/div/div/div/div[2]/div/form/ul/li[2]/span[2]/div/div[1]/span")).click();
        WebElement selectElement = chromeDriver.findElement(By.tagName("select"));
        //Select类用于定位select标签，将select封装select对象
        Select select = new Select(selectElement);

        Thread.sleep(3000);

        //1、获取所有选项：select.getOptions()
        List<WebElement> selectOptions = select.getOptions();
        for (WebElement option : selectOptions) {
            String tagName = option.getTagName();
            String text = option.getText();

            if ("女性".equals(text)){
                //（1）、根据可见的文本值选中某个的选项：select.selectByVisibleText(text)
                select.selectByVisibleText(text);

                //（2）、根据索引选中对应的元素：select.selectByIndex(index)
                select.selectByIndex(1);

                //（3）、选择指定value值对应的选项：select.selectByValue(value)
                select.selectByValue("female");
            }
        }
    }

    public static void openChrom(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.get(url);
    }

}
