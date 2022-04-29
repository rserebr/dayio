package com.dayio.automation.ui.components;

import com.codeborne.selenide.SelenideElement;
import com.dayio.automation.ui.core.utils.SelenideCondition;
import com.dayio.automation.ui.enums.NumberEmployees;
import com.dayio.automation.ui.pages.AccountTypePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class NavigationPanelComponent {

    SelenideElement activityTab = $x("//div[@class and text()='Activity']");

    @Step("Selecting Punches in navigation menu")
    public NavigationPanelComponent selectPunches() {
        activityTab.scrollIntoView(true).click();
        activityTab.$$x("./following-sibling::div//a").stream()
                .filter(e -> e.getText().equals("Punches"))
                .findFirst().get().shouldHave(SelenideCondition.jsLoaded).click();
        log.info("Activity -> Punches menu was selected");
        return this;
    }


}
