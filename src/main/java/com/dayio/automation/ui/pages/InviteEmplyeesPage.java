package com.dayio.automation.ui.pages;

import com.dayio.automation.ui.core.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class InviteEmplyeesPage extends BasePage<InviteEmplyeesPage> {
    protected InviteEmplyeesPage() {
        super(byXpath("//input[contains(@class, 'invite-employyes__button')]"));
    }

    @Step("Clicking skip button")
    public EmployeesPage clickSkipButton() {
        $x("//a[text()='Skip']").click();
        log.info("Company name is set");
        return new EmployeesPage().waitUntilPageWillOpen();
    }
}
