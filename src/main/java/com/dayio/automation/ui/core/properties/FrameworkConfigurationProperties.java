package com.dayio.automation.ui.core.properties;

import com.dayio.automation.ui.core.properties.converters.SecondsDurationConverter;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.*;

import java.time.Duration;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({"system:properties", "classpath:configurations/config.properties"})
public interface FrameworkConfigurationProperties extends Config {
    @Key("selenide.pageLoadStrategy")
    @DefaultValue("eager")
    String getPageLoadStrategy();

    @Key("selenide.browserSize")
    String getBrowserSize();

    @Key("selenide.screenshots")
    @DefaultValue("true")
    boolean isScreenshotsEnabled();

    @Key("selenide.savePageSource")
    @DefaultValue("true")
    boolean isSavePageSourceEnabled();

    @Key("page.load-timeout")
    @DefaultValue("35")
    @ConverterClass(SecondsDurationConverter.class)
    Duration getPageLoadTimeout();

    @Key("selenide.timeout")
    @DefaultValue("10")
    int getSelenideTimeout();

    @Key("selenide.screenshotPath")
    @DefaultValue("target/selenide/report")
    String getSelenideScreenshotPath();
}
