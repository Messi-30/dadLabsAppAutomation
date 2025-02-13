package com.dadlabs.utility;

import com.dadlabs.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {

    BaseTest baseTest;

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            result.getThrowable().printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        }


        baseTest = new BaseTest();
        Map<String, String> params = new HashMap<String, String>();
        params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String screenshotDirectory = "Screenshots" + File.separator + params.get("platformName") + "_" + params.get("platformVersion")
                + "_" + params.get("deviceName") + File.separator + baseTest.getCurrentDateTime() + File.separator + result.getTestClass()
                .getRealClass().getSimpleName() + File.separator + result.getName() + ".PNG";
        File screenshotFile = baseTest.getDriver().getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotDirectory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
