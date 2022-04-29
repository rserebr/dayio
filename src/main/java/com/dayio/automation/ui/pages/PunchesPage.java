package com.dayio.automation.ui.pages;

import com.dayio.automation.ui.components.PunchAdjustmentComponent;
import com.dayio.automation.ui.core.BasePage;
import com.dayio.automation.ui.core.utils.SelenideCondition;
import com.dayio.automation.ui.enums.PunchReason;
import com.dayio.automation.ui.enums.PunchType;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class PunchesPage extends BasePage<PunchesPage> {

    PunchAdjustmentComponent punchAdjustmentComponent = new PunchAdjustmentComponent();

    protected PunchesPage() {
        super(byXpath("//button[@state='secondary']"));
    }

    @Step("Clicking on add Punch Adjustment")
    public PunchesPage clickAddPunchAdjustment() {
        getWaitOpenPageElement().shouldHave(SelenideCondition.jsLoaded).click();
        log.info("Add Punch Adjustment clicked");
        return this;
    }

    @Step("Selecting employee as {name}")
    public PunchesPage selectEmployee(String name) {
        punchAdjustmentComponent.selectEmployee(name);
        log.info("Employee selected");
        return this;
    }

    @Step("Selecting date")
    public PunchesPage selectRequiredDate(int days) {
        punchAdjustmentComponent.setRequiredDateFromNow(days);
        return this;
    }

    @Step("Selecting time")
    public PunchesPage selectRequiredTime(String time) {
        punchAdjustmentComponent.setRequiredTime(time);
        return this;
    }

    @Step("Selecting Punch Type")
    public PunchesPage selectPunchType(PunchType punchType) {
        punchAdjustmentComponent.selectPunchType(punchType);
        return this;
    }

    @Step("Selecting Punch Location as {companyName}")
    public PunchesPage selectPunchLocation(String companyName) {
        punchAdjustmentComponent.selectLocation(companyName);
        return this;
    }

    public PunchesPage selectPunchReason(PunchReason punchReason) {
        punchAdjustmentComponent.selectPunchReason(punchReason);
        return this;
    }

    public PunchesPage confirmPunch() {
        punchAdjustmentComponent.clickConfirmButton();
        return this;
    }

    public String getSuccessfulMessageText() {
        return $x("//div[contains(@class, 'notification_employees-page notification_success')]").getText();
    }
}
