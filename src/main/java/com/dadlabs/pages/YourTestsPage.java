package com.dadlabs.pages;

import com.dadlabs.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class YourTestsPage extends BaseTest {

    @AndroidFindBy(accessibility = "Your tests")
    WebElement yourTestsPageTitle;

    public String getYourTestsPageTitle(){
        return getAttribute(yourTestsPageTitle, "content-desc");
    }
}
