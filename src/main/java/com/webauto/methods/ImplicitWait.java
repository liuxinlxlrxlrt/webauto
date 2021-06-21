package com.webauto.methods;

import com.webauto.base.Base;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 *  隐式等待(Implicit[ɪmˈplɪsɪt]  wait)
 */
public class ImplicitWait extends Base {

//打开网址过后，设置隐性等待,等待10秒
@Test
    public void test(){
    driver.manage().timeouts().implicitlyWait(10000,TimeUnit.SECONDS);
}


}
