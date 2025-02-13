package com.dadlabs.tests;

import com.dadlabs.base.BaseTest;
import com.dadlabs.pages.CreateAccountPage;
import com.dadlabs.pages.LandingPage;
import com.dadlabs.pages.LoginPage;
import com.dadlabs.pages.VerifyOtpPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateAccountPageTest extends BaseTest {
    LandingPage landingPage;
    LoginPage loginPage;
    VerifyOtpPage verifyOtpPage;
    CreateAccountPage createAccountPage;
    @BeforeClass
    public void setup() throws InterruptedException {
        landingPage = new LandingPage();
        loginPage = landingPage.skipGuideInfoOnLandingPage();
        loginPage.enterMobileNumber("9987731025");
        verifyOtpPage = loginPage.continueLogin();
        createAccountPage = verifyOtpPage.enterOtpForNewAccount();
    }
    @Test
    public void createAccountTest() throws InterruptedException {
        createAccountPage.createAccount("Sachin Darde","sachindarde@gmail.com","24", "Female", "");
    }
}
