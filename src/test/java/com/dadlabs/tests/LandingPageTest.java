package com.dadlabs.tests;

import com.dadlabs.base.BaseTest;
import com.dadlabs.pages.LandingPage;
import com.dadlabs.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LandingPageTest extends BaseTest {
    LandingPage landingPage;
    LoginPage loginPage;

    @BeforeMethod
    public void setup(Method m)
    {
        System.out.println("Starting test - "+ m.getName()+ "\n");
        landingPage = new LandingPage();
    }

    @Test
    public void skipGuideInfoOnLandingPageTest(){
        loginPage = landingPage.skipGuideInfoOnLandingPage();
        testUtils.log().info("Clicked on Skip button.");
        Assert.assertEquals(loginPage.getLoginPageText(), "Login to your account to book tests.");
    }
    @Test(enabled = false)
    public void exploreGuideInfoTest(){
    loginPage = landingPage.exploreGuideInfo();
    Assert.assertEquals(loginPage.getLoginPageText(), "Login to your account to book tests.");
    }

    @Test(enabled = false)
    public void swipeGuideInfoTest(){
        loginPage = landingPage.swipeGuideInfo();
        Assert.assertEquals(loginPage.getLoginPageText(), "Login to your account to book tests.");
    }
    
}
