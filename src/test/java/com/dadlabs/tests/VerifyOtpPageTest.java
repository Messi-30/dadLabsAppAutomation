package com.dadlabs.tests;

import com.dadlabs.base.BaseTest;
import com.dadlabs.pages.LandingPage;
import com.dadlabs.pages.LoginPage;
import com.dadlabs.pages.VerifyOtpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyOtpPageTest extends BaseTest {
    LandingPage landingPage;
    LoginPage loginPage;
    VerifyOtpPage verifyOtpPage;
    @BeforeClass
    public void setup()
    {
        landingPage = new LandingPage();
        loginPage = landingPage.skipGuideInfoOnLandingPage();
        loginPage.enterMobileNumber("9987731025");
        verifyOtpPage = loginPage.continueLogin();
    }
    @Test(priority = 1, enabled = false)
    public void verifySentOtpMobileNoTest(){
        Assert.assertEquals(verifyOtpPage.verifySentOtpMobileNo(),"Please enter the OTP sent to\n" +
                "+919987731025");
    }

    @Test
    public void enterOtpTest() throws InterruptedException {
        verifyOtpPage.enterOtp();
    }


}
