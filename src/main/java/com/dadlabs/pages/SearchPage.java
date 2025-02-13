package com.dadlabs.pages;

import com.dadlabs.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SearchPage extends BaseTest {
    @AndroidFindBy(accessibility = "Search")
    WebElement searchPageTitle;

    public String getSearchPageTitle(){
        return getAttribute(searchPageTitle, "content-desc");
    }
}
