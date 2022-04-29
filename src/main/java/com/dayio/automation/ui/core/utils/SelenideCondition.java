package com.dayio.automation.ui.core.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import lombok.NonNull;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public final class SelenideCondition {
    public static final Condition jsLoaded = new Condition("jsLoaded") {
        public boolean apply(@NonNull Driver driver, @NonNull WebElement webElement) {
            return Objects.requireNonNull(Selenide.executeJavaScript("return document.readyState")).toString().equals("complete");
        }
    };

    private SelenideCondition() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
