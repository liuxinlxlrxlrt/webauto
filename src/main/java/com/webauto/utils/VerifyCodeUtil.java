package com.webauto.utils;

import com.webauto.base.Base;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class VerifyCodeUtil  {

    public static final String COOKIE_NAME="verifycode";

    public static String getVerifyCode(){
        return Base.driver.manage().getCookieNamed(COOKIE_NAME).getValue();
    }
}
