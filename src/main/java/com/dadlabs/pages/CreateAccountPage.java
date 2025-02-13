package com.dadlabs.pages;

import com.dadlabs.base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CreateAccountPage extends BaseTest {


    @AndroidFindBy(accessibility = "Let us know you")
    WebElement createAccountPageTitle;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Full name\"]/following-sibling::android.widget.EditText[1]")
    WebElement nameEditText;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Email id (optional)\"]/following-sibling::android.widget.EditText[1]")
    WebElement emailEditText;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Your age\"]/following-sibling::android.widget.EditText[1]")
    WebElement ageEditText;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Your sex\"]/following-sibling::android.view.View[1]/android.view.View")
    WebElement sexRadioGroup;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"I have a referral code\"]/preceding-sibling::android.view.View")
    WebElement haveReferralCode;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"I have a referral code\"]/following-sibling::android.widget.EditText[1]")
    WebElement referralEditText;

    @AndroidFindBy(accessibility = "Create account")
    WebElement createAccountBtn;

    public void createAccount(String fullName, String email, String age, String gender, String referralCode) throws InterruptedException {
        clickOn(nameEditText);
        fillInTextFields(nameEditText, fullName);
        clickOn(emailEditText);
        fillInTextFields(emailEditText, email);
        clickOn(ageEditText);
        fillInTextFields(ageEditText, age);
        ((AndroidDriver)getDriver()).hideKeyboard();
        selectRadioOption(gender);
        if(referralCode != "")
        {
            clickOn(haveReferralCode);
            fillInTextFields(referralEditText, referralCode);
        }
        clickOn(createAccountBtn);
    }

}
