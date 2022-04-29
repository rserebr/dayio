package com.dayio.automation.ui.core;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.dayio.automation.ui.core.properties.FrameworkConfigurationProperties;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.function.BiConsumer;

public abstract class BasePage<PAGE extends BasePage<PAGE>> {
    private static final Logger log = LogManager.getLogger(BasePage.class);
    @Getter(AccessLevel.PROTECTED)
    private String pageURL;

    @Getter(AccessLevel.PROTECTED)
    private SelenideElement waitOpenPageElement;

    private FrameworkConfigurationProperties frameworkConfigurationProperties =
            ConfigFactory.create(FrameworkConfigurationProperties.class);

    private BiConsumer<SelenideElement, Duration> defaultWaitOpenPageLogic;

    protected BasePage(By waitOpenPageLocator) {
        this.defaultWaitOpenPageLogic = (waitOpenPageElement, duration) -> {
            waitOpenPageElement.shouldBe(Condition.visible, duration);
        };
        this.waitOpenPageElement = Selenide.$(waitOpenPageLocator);
    }

    protected BasePage(String pageURL, By waitOpenPageLocator) {
        this(waitOpenPageLocator);
        this.pageURL = pageURL;
    }

    @Step("Opening page")
    public PAGE openPage() {
        if (this.pageURL != null) {
            log.debug("Trying to open '{}' page", this.getClass().getSimpleName());
            Selenide.open(this.pageURL);
            log.debug("Opening '{}' page URL", this.pageURL);
            return (PAGE) this;
        } else {
            log.error("Page URL is empty, probably not establish");
            throw new UnsupportedOperationException("Page URL not establish");
        }
    }

    public PAGE waitUntilPageWillOpen(Duration duration) {
        String pageName = this.getClass().getSimpleName();
        log.debug("Waiting until '{}' page will open", pageName);
        this.defaultWaitOpenPageLogic.accept(this.waitOpenPageElement, duration);
        log.info("'{}' page was opened", pageName);
        return (PAGE) this;
    }

    @Step("Waiting for page to be loaded...")
    public PAGE waitUntilPageWillOpen() {
        this.waitUntilPageWillOpen(this.frameworkConfigurationProperties.getPageLoadTimeout());
        return (PAGE) this;
    }



}
