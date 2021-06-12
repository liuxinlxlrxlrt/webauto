package com.webauto.utils;

import com.webauto.base.Base;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Assertion {

    public static void assertUrlContains(String urlContains){
        WebDriverWait wait = new WebDriverWait(Base.driver,30);
        boolean isDirectedToLogin=true;
        try {
            wait.until(ExpectedConditions.urlContains(urlContains));
        } catch (Exception e) {
            isDirectedToLogin=false;
        }
        Assert.assertEquals(isDirectedToLogin,true);
    }
}
