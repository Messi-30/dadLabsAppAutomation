package com.dadlabs.pages;

import com.dadlabs.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends BaseTest {

    @AndroidFindBy(accessibility = "Home")
    WebElement homeMenu;

    @AndroidFindBy(accessibility = "Search")
    WebElement searchMenu;

    @AndroidFindBy(accessibility = "Your tests")
    WebElement yourTestsMenu;

    @AndroidFindBy(accessibility = "Profile")
    WebElement profileMenu;

    public SearchPage navigateToSearchPage() throws InterruptedException {
        Thread.sleep(5000);
        clickOn(searchMenu);
        return new SearchPage();
    }

    public YourTestsPage navigateToYourTestsPage() throws InterruptedException {
        Thread.sleep(5000);
        clickOn(yourTestsMenu);
        return new YourTestsPage();
    }

    public ProfilePage navigateToProfilePage() throws InterruptedException {
        Thread.sleep(5000);
        clickOn(profileMenu);
        return new ProfilePage();
    }
}
