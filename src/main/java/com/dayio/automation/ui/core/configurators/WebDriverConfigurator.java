package com.dayio.automation.ui.core.configurators;

import com.codeborne.selenide.Configuration;
import com.dayio.automation.ui.core.properties.FrameworkConfigurationProperties;
import lombok.NonNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverConfigurator {
    private static final Logger log = LogManager.getLogger(WebDriverConfigurator.class);
    private boolean webDriverConfigured;
    @NonNull
    private FrameworkConfigurationProperties frameworkConfigurationProperties;

    public void configure() {
        if (!this.webDriverConfigured) {
            Configuration.savePageSource = this.frameworkConfigurationProperties.isSavePageSourceEnabled();
            Configuration.screenshots = this.frameworkConfigurationProperties.isScreenshotsEnabled();
            Configuration.timeout = this.frameworkConfigurationProperties.getSelenideTimeout() * 1000L;
            Configuration.pageLoadStrategy = this.frameworkConfigurationProperties.getPageLoadStrategy();
            Configuration.browserSize = this.frameworkConfigurationProperties.getBrowserSize();
            Configuration.reportsFolder = this.frameworkConfigurationProperties.getSelenideScreenshotPath();
            this.webDriverConfigured = true;
            log.info("WebDriver configured");
        }
    }

    public WebDriverConfigurator(@NonNull FrameworkConfigurationProperties frameworkConfigurationProperties) {
            this.frameworkConfigurationProperties = frameworkConfigurationProperties;
        }
}