package com.dadlabs.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends MenuPage {

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Hello\"]/following-sibling::android.view.View")
    WebElement userName;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Hello\"]/following-sibling::android.widget.ImageView[2]")
    WebElement cart;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Hello\"]/following-sibling::android.widget.ImageView[3]")
    WebElement searchTest;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@text=\"Search Tests\"]/following-sibling::android.view.View[1]/android.view.View")
    WebElement specializedTestsScroller;

    @AndroidFindBy(accessibility = "Popular Tests")
    WebElement popularTestsSection;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Popular Tests\"]/following-sibling::android.view.View[1]")
    WebElement expandPopularTestSection;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Popular Tests\"]/following-sibling::android.view.View[2]/android.view.View[@scrollable=\"true\"]")
    WebElement popularTestsScroll;

    @AndroidFindBy(className= "android.widget.ScrollView")
    WebElement homePageScrollView;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Limited time deals\"]")
    WebElement limitedDeals;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Customer Feedbacks\"]")
    WebElement customerFeedbackSection;


    public String getUserName(){
        return getAttribute(userName, "content-desc");
    }

    public YourCartPage navigateToCart() throws InterruptedException {
        Thread.sleep(5000);
        clickOn(cart);
        return new YourCartPage();
    }

    public void viewFromSpecializedTests(String searchText) throws InterruptedException {
        Thread.sleep(10000);
        List<WebElement> searchedSpecializedTest;
        searchedSpecializedTest =specializedTestsScroller.findElements(AppiumBy.xpath(".//android.widget.ImageView[contains(@content-desc,\""+searchText+"\")]"));

        if(searchedSpecializedTest.size()<=0){
            swipeLeftToElement(searchText);
        }
        else{
            searchedSpecializedTest.get(0).click();
        }
    }

    public TestDetailsPage selectFromPopularTests(String searchText){
        swipeLeftToElement(searchText);
        List<WebElement> searchedPopularTest;
        searchedPopularTest =popularTestsScroll.findElements(AppiumBy.xpath(".//android.widget.ImageView[contains(@content-desc,\""+searchText+"\")]"));
        searchedPopularTest.get(0).click();
        return new TestDetailsPage();
    }

    public PopularTestsPage getAllPopularTests(){
        clickOn(expandPopularTestSection);
        return new PopularTestsPage();
    }

    public TestDetailsPage selectFromLimitedTimeDealsTests(String searchText){
        scrollToElement(searchText);
        List<WebElement> LimitedTimeDealsTest;
        LimitedTimeDealsTest =getDriver().findElements(AppiumBy.xpath("//android.view.View[contains(@content-desc,\""+searchText+"\")]"));
        LimitedTimeDealsTest.get(0).click();
        return new TestDetailsPage();
    }

    public void getTestOffersWithDiscount() throws InterruptedException {
        List<WebElement> offers = new ArrayList<WebElement>();
        List<WebElement> tempOffers= new ArrayList<WebElement>();

        Thread.sleep(10000);
        scrollGestureWithWebElement(homePageScrollView);
        tempOffers=homePageScrollView.findElements(AppiumBy.xpath("//android.view.View[contains(@content-desc,\"OFF\") and @clickable= \"false\"]"));
        offers.addAll(tempOffers);

        Thread.sleep(10000);
        scrollGestureWithWebElement(homePageScrollView);
        tempOffers=homePageScrollView.findElements(AppiumBy.xpath("//android.view.View[contains(@content-desc,\"OFF\") and @clickable= \"false\"]"));
        offers.addAll(tempOffers);

        System.out.println(offers);
        System.out.println(offers.size());

    }

    public void getTestsWithSpecifiedOffer(String offerName){
        scrollToElement(offerName);
        homePageScrollView.findElement(AppiumBy.xpath(".//android.view.View[contains(@content-desc,\"" + offerName + "\") and @clickable= \"false\"]/following-sibling::android.view.View[@clickable= \"true\"][1]")).click();

    }




}
