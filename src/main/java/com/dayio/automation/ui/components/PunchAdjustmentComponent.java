package com.dayio.automation.ui.components;

import com.dayio.automation.ui.core.utils.SelenideCondition;
import com.dayio.automation.ui.enums.PunchReason;
import com.dayio.automation.ui.enums.PunchType;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class PunchAdjustmentComponent {

    CalendarComponent calendarComponent = new CalendarComponent();

    public PunchAdjustmentComponent selectEmployee(String employee) {
        log.info("Selecting employee name as " + employee);
        $x("//div[@class='sc-cJSrbW dhPXAf']//div[@type='person']/following-sibling::input").setValue(employee);
        $x("//div[@class='search-control-new__dropdown']//div[@class='user__name']/span[text()='"+employee+"']")
                .shouldHave(SelenideCondition.jsLoaded)
                .click();
        return this;
    }

    public PunchAdjustmentComponent setRequiredDateFromNow(int days) {
        calendarComponent.selectDateInRangeFormNow(days);
        return this;
    }

    public PunchAdjustmentComponent setRequiredTime(String time) {
        $(byName("time")).setValue(time);
        log.info("Time is set as " + time);
        return this;
    }

    public PunchAdjustmentComponent selectPunchType(PunchType punchType) {
        $x("//div[@class='ui-select__input' and text()='Select a category']").click();
        $$x("//div[contains(@class, 'sc-kPVwWT eQVhED')]").stream()
                .filter(e -> e.getText().equals(punchType.getValue()))
                .findFirst().get().click();
        log.info("Punch type is set");
        return this;
    }

    public PunchAdjustmentComponent selectLocation(String companyName) {
        $x("//div[@class='sc-eerKOB graRXw searchable']").click();
        $$x("//div[contains(@id,'react-select-')]").stream()
                .filter(e -> e.getText().equals(companyName))
                .findFirst().get().click();
        log.info("Location is set");
        return this;
    }

    public PunchAdjustmentComponent selectPunchReason(PunchReason punchReason) {
        $x("//div[@class='ui-select__input' and text()='Select the reason']").click();
        $$x("//div[contains(@class,'sc-kPVwWT eQVhED')]").stream()
                .filter(e -> e.getText().equals(punchReason.getValue()))
                .findFirst().get().click();
        log.info("Punch reason is set");
        return this;
    }

    public void clickConfirmButton() {
        $x("//button[@class='sc-kTUwUJ gOwkZj']").click();
        log.info("Confirm button is clicked");
    }
}
