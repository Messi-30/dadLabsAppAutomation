package com.dadlabs.tests;

import com.dadlabs.base.BaseTest;
import com.dadlabs.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class HomePageTest extends BaseTest {
    HomePage homePage;
    LandingPage landingPage;
    LoginPage loginPage;
    VerifyOtpPage verifyOtpPage;
    YourCartPage yourCartPage;
    SearchPage searchPage;
    YourTestsPage yourTestsPage;
    ProfilePage profilePage;
    TestDetailsPage testDetailsPage;
    PopularTestsPage popularTestsPage;

    MenuPage menuPage;

    @BeforeMethod
    public void setup(Method m) throws InterruptedException {
        System.out.println("Starting test - "+ m.getName()+ "\n");
        landingPage = new LandingPage();
        loginPage = landingPage.skipGuideInfoOnLandingPage();
        loginPage.enterMobileNumber("9987731025");
        verifyOtpPage = loginPage.continueLogin();
        homePage = verifyOtpPage.enterOtp();
    }
    @Test(priority = 1, enabled = false)
    public void validateUserNameTest(){
        Assert.assertEquals(homePage.getUserName(),"Sachin");
    }

    @Test(dependsOnMethods ={"validateUserNameTest"},enabled = false )
    public void validateCartNavigationTest() throws InterruptedException {
        yourCartPage = homePage.navigateToCart();
        Assert.assertEquals(yourCartPage.getCartPageTitle(),"Your Cart");
    }

    @Test(dependsOnMethods ={"validateUserNameTest"},enabled = false)
    public void validateSearchPageNavigationTest() throws InterruptedException {
        searchPage = menuPage.navigateToSearchPage();
        Assert.assertEquals(searchPage.getSearchPageTitle(),"Search");
    }

    @Test(enabled = false)
    public void validateYourTestsPageNavigationTest() throws InterruptedException {
        yourTestsPage = menuPage.navigateToYourTestsPage();
        Assert.assertEquals(yourTestsPage.getYourTestsPageTitle(),"Your tests");
    }

    @Test(enabled = false)
    public void validateProfilePageNavigationTest() throws InterruptedException {
        profilePage = menuPage.navigateToProfilePage();
    }
    @Test(enabled = false)
    public void validateViewSpecializedTests() throws InterruptedException {
        homePage.viewFromSpecializedTests("RTPCR");
    }

    @Test(enabled = false)
    public void validateSelectFromPopularTests()
    {
        testDetailsPage = homePage.selectFromPopularTests("FibroTest");
    }
    @Test(enabled = false)
    public void verifyGetAllPopularTests(){
        popularTestsPage = homePage.getAllPopularTests();
    }

    @Test(enabled = false)
    public void verifySelectFromLimitedTimeDealsTests()
    {
        testDetailsPage = homePage.selectFromLimitedTimeDealsTests("100RSFLAT");
    }
    @Test(enabled = false)
    public void getTestOffersWithDiscountTest() throws InterruptedException {
        homePage.getTestOffersWithDiscount();
    }
    @Test(enabled = true)
    public void getTestsWithSpecifiedOfferTest(){
        homePage.getTestsWithSpecifiedOffer("100RSOFF");
    }
}

