package com.dadlabs.pages;
import com.dadlabs.base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LoginPage extends BaseTest {

    @AndroidFindBy(accessibility = "Login to your account to book tests.")
    WebElement loginPageText;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Continue\"]/preceding-sibling::android.widget.EditText/android.view.View")
    WebElement countryCode;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Continue\"]/preceding-sibling::android.widget.EditText")
    WebElement mobileNo;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@text,\"Search for your\")]")
    WebElement searchCountryCode;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@text=\"Search for your country\"]/preceding-sibling::android.view.View")
    WebElement closeCountryCodePopUp;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@text,\"Search for your\")]/following-sibling::android.view.View/android.view.View")
    WebElement countryListView;

    @AndroidFindBy(accessibility = "Continue")
    WebElement continueBtn;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Continue\"]/preceding-sibling::android.widget.EditText/android.view.View[2]")
    WebElement requiredRemark;


    public void selectCountryCode(String searchText, String countryName) {
        clickOn(countryCode);
        clickOn(searchCountryCode);
        fillInTextFields(searchCountryCode,searchText);
        ((AndroidDriver)getDriver()).hideKeyboard();
        scrollToElement(countryName);

        List<WebElement> searchedCountry;
        searchedCountry =countryListView.findElements(AppiumBy.xpath(".//android.widget.ImageView[contains(@content-desc,\""+countryName+"\")]"));
        searchedCountry.get(0).click();
    }

    public String enterMobileNumber(String mobile){
        clickOn(mobileNo);
        fillInTextFields(mobileNo,mobile);
        String [] contact = getAttribute(mobileNo,"text").split(",");
        return contact[0];
    }

    public VerifyOtpPage continueLogin(){
        clickOn(continueBtn);
        return new VerifyOtpPage();
    }

    public String validateRequiredRemark() throws InterruptedException {
        clickOn(continueBtn);
        Thread.sleep(3000);
        return getAttribute(requiredRemark, "content-desc");
    }

    public String getLoginPageText() {
        return getAttribute(loginPageText, "content-desc");
    }


}
