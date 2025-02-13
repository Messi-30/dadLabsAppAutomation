package com.dadlabs.pages;

import com.dadlabs.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class YourCartPage extends BaseTest {
    @AndroidFindBy(accessibility = "Your Cart")
    WebElement cartPageTitle;

    public String getCartPageTitle(){
        return getAttribute(cartPageTitle, "content-desc");
    }
}
