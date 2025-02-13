package com.dadlabs.base;

import com.dadlabs.utility.TestUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class BaseTest {
    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal<Properties> property = new ThreadLocal<Properties>();;
    protected static ThreadLocal<String> currentDateTime = new ThreadLocal<String>();
    protected ThreadLocal<String> platform= new ThreadLocal<String>();
    protected ThreadLocal<String> deviceName= new ThreadLocal<String>();
    private static AppiumDriverLocalService server;

    protected TestUtils testUtils;

    public AppiumDriver getDriver(){
        return driver.get();
    }

    public void setDriver(AppiumDriver appiumDriver){
        driver.set(appiumDriver);
    }

    public Properties getProperty(){
        return property.get();
    }
    public void setProperty(Properties properties){
        property.set(properties);
    }

    public String getCurrentDateTime(){
        return currentDateTime.get();
    }

    public void setCurrentDateTime(String dateTime){
        currentDateTime.set(dateTime);
    }

    public String getPlatform(){
        return platform.get();
    }

    public void setPlatform(String platformName){
        platform.set(platformName);
    }

    public String getDeviceName(){
        return deviceName.get();
    }

    public void setDeviceName(String device){
        deviceName.set(device);
    }


    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @BeforeSuite
    public void startAppiumServer() throws Exception {
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
        testUtils = new TestUtils();
//		server = getAppiumService(); // -> If using Mac, uncomment this statement and comment below statement
        server = getAppiumServerDefault(); // -> If using Windows, uncomment this statement and comment above statement
        if(!checkIfAppiumServerIsRunnning(4723)) {
            server.start();
            server.clearOutPutStreams(); // -> Comment this if you want to see server logs in the console
            testUtils.log().info("Appium server started");
        } else {
            testUtils.log().info("Appium server already running");
        }

    }

    public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
        boolean isAppiumServerRunning = false;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            System.out.println("1");
            isAppiumServerRunning = true;
        } finally {
            socket = null;
        }
        return isAppiumServerRunning;
    }

    @AfterSuite
    public void stopAppiumServer(){
        server.stop();
    }

    public AppiumDriverLocalService getAppiumServerDefault(){
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService getAppiumService(){

        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withAppiumJS(new File("C:\\Users\\DEV\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE));
    }

    @BeforeTest
    @Parameters({"emulator","platformName","platformVersion","udid","deviceName","systemPort","chromeDriverPort","wdaLocalPort","webkitDebugProxyPort"})
    public synchronized void setup(@Optional("androidOnly") String isEmulator, String platformName, String platformVersion, String udid, String deviceName, @Optional("androidOnly") String systemPort, @Optional("androidOnly") String chromeDriverPort, @Optional("iOSOnly")String wdaLocalPort,@Optional("iOSOnly") String webkitDebugProxyPort )
    {
        FileInputStream fis;
        URL url;
        AppiumDriver driver;
        TestUtils testUtils = new TestUtils();

        String strFile = "logs" + File.separator + platformName + "_" + deviceName;
        File logFile = new File(strFile);
        if (!logFile.exists()) {
            logFile.mkdirs();
        }
        //route logs to separate file for each thread
        ThreadContext.put("ROUTINGKEY", strFile);
        testUtils.log().info("log path: " + strFile);

        setCurrentDateTime(testUtils.getDateTime());
        try {
            fis = new FileInputStream("C:\\Users\\DEV\\Intellij IDEA projects\\dadLabsAppAutomation\\src\\main\\resources\\config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Properties property = new Properties();

        try {
            property.load(fis);
            setProperty(property);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("deviceName", deviceName);

        switch (platformName){
            case "Android":
                desiredCapabilities.setCapability("automationName", property.getProperty("androidAutomationName"));
                desiredCapabilities.setCapability("appPackage", property.getProperty("androidAppPackage"));
                desiredCapabilities.setCapability("appActivity", property.getProperty("androidAppActivity"));
                desiredCapabilities.setCapability("systemPort", systemPort);
                desiredCapabilities.setCapability("chromeDriverPort", chromeDriverPort);
                desiredCapabilities.setCapability("app",property.getProperty("androidAppLocation"));
                //desiredCapabilities.setCapability("noReset",false);
                //desiredCapabilities.setCapability("fullReset", false);
                desiredCapabilities.setCapability("autoGrantPermissions",true);


                if(isEmulator.equalsIgnoreCase("true")){
                    //desiredCapabilities.setCapability("avd", deviceName);
                    desiredCapabilities.setCapability("udid", udid);
                    try {
                        url = new URL(property.getProperty("appiumServerURL")+ "4723/");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    desiredCapabilities.setCapability("udid", udid);
                    try {
                        url = new URL(property.getProperty("appiumServerURL") + "4723/");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }
                driver = new AndroidDriver(url, desiredCapabilities);
                setDriver(driver);
                break;
            case "iOS":
                desiredCapabilities.setCapability("automationName", property.getProperty("iOSAutomationName"));
                desiredCapabilities.setCapability("iOSBundleId", property.getProperty("<iOSBundleId>"));
                desiredCapabilities.setCapability("app",property.getProperty("iOSAppLocation"));
                desiredCapabilities.setCapability("wdaLocalPort", wdaLocalPort);
                desiredCapabilities.setCapability("webkitDebugProxyPort", webkitDebugProxyPort);
                desiredCapabilities.setCapability("noReset",true);
                desiredCapabilities.setCapability("fullReset", false);
                desiredCapabilities.setCapability("autoGrantPermissions",true);

                try {
                    url = new URL(property.getProperty("appiumServerURL"));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver = new IOSDriver(url, desiredCapabilities);
                break;
            default:
                throw new RuntimeException("Invalid Platform Name !");

        }

    }
    /*@BeforeMethod
    public void startRecording(){
        ((CanRecordScreen)getDriver()).startRecordingScreen();
        //driver.executeScript("mobile:shell", ImmutableMap.of("command", "screenrecord /bin");
       *//* try {
            ((AndroidDriver)driver).startRecordingScreen(
                    new AndroidStartScreenRecordingOptions()
                            .withTimeLimit(Duration.ofSeconds(5))
            );
        } catch (WebDriverException e) {
            if (e.getMessage().toLowerCase().contains("emulator")) {
                // screen recording only works on real devices
                return;
            }
        }*/

       // driver.executeScript("mobile:shell", ImmutableMap.of("command", "screenrecord /sdcard/demo.mp4 --time-limit 120"));
   // }
    /*@AfterMethod
    public synchronized void stopRecording(ITestResult result){
        String media=  ((CanRecordScreen)getDriver()).stopRecordingScreen();

        Map<String, String> params = new HashMap<String, String>();
        params =result.getTestContext().getCurrentXmlTest().getAllParameters();

        String recordingDirectory = "Recordings" + File.separator + params.get("platformName")+"_"+params.get("platformVersion")
                +"_"+params.get("deviceName")+File.separator + getCurrentDateTime() +File.separator + result.getTestClass()
                .getRealClass().getSimpleName();

        File recordingFile = new File(recordingDirectory);

        synchronized (recordingFile){
            if(!recordingFile.exists()){
                recordingFile.mkdirs();
            }
        }


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(recordingDirectory + File.separator +result.getName() + ".mp4");
            fileOutputStream.write(Base64.decodeBase64(media));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/


    public void waitForVisibility(WebElement e){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TestUtils.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public void clickOn(WebElement element){
        waitForVisibility(element);
        element.click();
    }
    public void fillInTextFields(WebElement element, String text){
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public WebElement scrollToElement(String elementStr){
        WebElement element =((AndroidDriver)getDriver()).findElement(AppiumBy.androidUIAutomator("new UiScrollable(UiSelector().scrollable(true)).setAsVerticalList().setMaxSearchSwipes(30)" +
                ".scrollIntoView(new UiSelector().descriptionContains(\""+elementStr+"\"))"));
        return element;
    }

    public WebElement scrollToEndOnScrollView(){
        WebElement element =((AndroidDriver)getDriver()).findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).setAsVerticalList().scrollToEnd(10,10)" ));
        List<WebElement> offers;

        return element;
    }
    public WebElement swipeLeftToElement(String elementStr){
        WebElement element =((AndroidDriver)getDriver()).findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().className(\"android.view.View\").index(9).childSelector(new UiSelector().className(\"android.view.View\").scrollable(true))).setAsHorizontalList().setMaxSearchSwipes(10)" +
                ".scrollIntoView(new UiSelector().descriptionContains(\""+elementStr+"\"))"));
        return element;
    }

    public WebElement swipeLeftWithUiSelector(int maxSearchSwipes, int index ){
        WebElement element =((AndroidDriver)getDriver()).findElement(AppiumBy.androidUIAutomator("new UiScrollable().setAsHorizontalList().setMaxSearchSwipes(10)" +
                ".scrollIntoView(new UiSelector().className(\"android.widget.ImageView\").index(0)"));
        return element;
    }

    public void selectRadioOption(String radioOption){
        getDriver().findElement(AppiumBy.xpath("//android.widget.RadioButton[@content-desc=\""+radioOption+"\"]"));
    }

    public void clickGestureWithCords(int x, int y){
        getDriver().executeScript("mobile: clickGesture", ImmutableMap.of(
                "x",137 ,
                "y",767
        ));
    }

    public void scrollGestureWithWebElement(WebElement scrollview ) {
        boolean canScrollMore=true;
        while (canScrollMore) {
            canScrollMore = (Boolean) getDriver().executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) scrollview).getId(),
                    "direction", "down",
                    "percent", 1.0
            ));

        }
    }

    public void closeApplication(){
        ((InteractsWithApps)getDriver()).terminateApp(getProperty().getProperty("androidAppPackage"));
    }

    public void launchApplication(){
        ((InteractsWithApps)getDriver()).activateApp(getProperty().getProperty("androidAppPackage"));
    }
    @AfterTest
    public void quiteDriver(){
        //driver.quit();
    }
}
