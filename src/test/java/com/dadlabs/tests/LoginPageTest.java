package com.dadlabs.tests;

import com.dadlabs.base.BaseTest;
import com.dadlabs.pages.LandingPage;
import com.dadlabs.pages.LoginPage;
import com.dadlabs.pages.VerifyOtpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    LandingPage landingPage;
    LoginPage loginPage;
    VerifyOtpPage verifyOtpPage;
    @BeforeClass
    public void setup()
    {
        landingPage = new LandingPage();
        loginPage = landingPage.skipGuideInfoOnLandingPage();
    }
    @Test(priority = 2)
    public void selectCountryCodeTest(){
        loginPage.selectCountryCode("","India");
    }
    @Test(dependsOnMethods = "selectCountryCodeTest", priority = 3)
    public void enterMobileNoTest(){
        Assert.assertEquals(loginPage.enterMobileNumber("9987731025"),"9987731025");
    }

    @Test(dependsOnMethods = { "selectCountryCodeTest","enterMobileNoTest"}, priority = 4)
    public void ContinueLoginTest(){
        verifyOtpPage = loginPage.continueLogin();
        Assert.assertEquals(verifyOtpPage.getVerifyOtpPageTitle(), "Verify Phone");
    }
    @Test(priority = 1)
    public void validateRequiredRemarkTest() throws InterruptedException {
        Assert.assertEquals(loginPage.validateRequiredRemark(),"Required");
    }


}


