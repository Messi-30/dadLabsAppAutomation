package com.dadlabs.pages;

import com.dadlabs.base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LandingPage extends BaseTest {
    @AndroidFindBy(xpath ="//android.widget.Button[@content-desc=\"Skip\"]/preceding-sibling::android.view.View[2]/android.view.View/android.widget.ImageView")
    WebElement appGuidePages;
    @AndroidFindBy(accessibility = "Skip")
    WebElement skipBtn;
    @AndroidFindBy(accessibility = "Next")
    WebElement nextBtn;



    public LoginPage skipGuideInfoOnLandingPage()
    {
        clickOn(skipBtn);
        return new LoginPage();
    }

    public LoginPage exploreGuideInfo(){
       // System.out.println(getAttribute(appGuidePages, "content-desc"));
        clickOn(nextBtn);
       // System.out.println(getAttribute(appGuidePages, "content-desc"));
        clickOn(nextBtn);
       // System.out.println(getAttribute(appGuidePages, "content-desc"));
        clickOn(nextBtn);
        return new LoginPage();
    }

    public LoginPage swipeGuideInfo(){
        swipeLeftWithUiSelector(3,0);
        return new LoginPage();
    }
}
