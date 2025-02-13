package com.dadlabs.pages;

import com.dadlabs.base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class VerifyOtpPage extends BaseTest {

    @AndroidFindBy(accessibility = "Verify Phone")
    WebElement verifyPhone;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, \"Please enter the OTP sent to\")]")
    WebElement otpOnMobile;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, \"Please enter the OTP sent to\")]/following-sibling::android.view.View")
    WebElement otpBox;

    @AndroidFindBy(accessibility = "Didn’t received? ")
    WebElement resendOtpText;

    @AndroidFindBy(accessibility = "Resend")
    WebElement resendBtn;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Resend\"]/following-sibling::android.view.View[1]")
    WebElement otpTimeout;

    public String getVerifyOtpPageTitle(){
        return getAttribute(verifyPhone, "content-desc");
    }

    public String verifySentOtpMobileNo(){
        return getAttribute(otpOnMobile, "content-desc");
    }
    public CreateAccountPage enterOtpForNewAccount() throws InterruptedException {

        Thread.sleep(10000);
        clickGestureWithCords(137,767);

        System.out.println(((AndroidDriver)getDriver()).isKeyboardShown());
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_3));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_5));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_6));
        return new CreateAccountPage();
    }
    public HomePage enterOtp() throws InterruptedException {

        Thread.sleep(10000);
        clickGestureWithCords(137,767);

        System.out.println(((AndroidDriver)getDriver()).isKeyboardShown());
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_3));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_5));
        ((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.DIGIT_6));
        return new HomePage();
    }
}
