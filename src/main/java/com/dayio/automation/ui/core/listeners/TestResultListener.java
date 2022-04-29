package com.dayio.automation.ui.core.listeners;

import com.dayio.automation.ui.core.configurators.WebDriverConfigurator;
import com.dayio.automation.ui.core.properties.FrameworkConfigurationProperties;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;

public class TestResultListener implements ITestListener {

    private static final FrameworkConfigurationProperties FRAMEWORK_CONFIGURATION_PROPERTIES =
            ConfigFactory.create(FrameworkConfigurationProperties.class);

    @Override
    public void onTestStart(ITestResult result) {
        WebDriverConfigurator webConfig = new WebDriverConfigurator(FRAMEWORK_CONFIGURATION_PROPERTIES);
        webConfig.configure();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] takeScreenshot() {
        File[] file = new File(FRAMEWORK_CONFIGURATION_PROPERTIES.getSelenideScreenshotPath()).listFiles();
        byte[] image = null;
        try {
            image = Files.readAllBytes(file[0].toPath());
        } catch (Exception e) { }
        return image;
    }
}
