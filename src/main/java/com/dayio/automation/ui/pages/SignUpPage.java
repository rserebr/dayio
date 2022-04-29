package com.dayio.automation.ui.pages;

import com.dayio.automation.ui.core.BasePage;
import com.dayio.automation.ui.core.utils.SelenideCondition;
import com.dayio.automation.ui.domains.AdminUser;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class SignUpPage extends BasePage<SignUpPage> {

    protected SignUpPage() {
        super(byXpath("//button[text()='Continue']"));
    }

    @Step("Filling registration form")
    public SignUpPage fillRegisterForm(AdminUser adminUser) {
        setFullName(adminUser.getFullName());
        setEmail(adminUser.getEmail());
        setPassword(adminUser.getPassword());
        setPhoneNumber(adminUser.getMobilePhone());
        return this;
    }

    @Step("Setting '{fullName}' into Full Name")
    public SignUpPage setFullName(String fullName) {
        $(byName("firstname")).setValue(fullName);
        log.info("Full name is set");
        return this;
    }

    @Step("Setting '{email}' into email field")
    public SignUpPage setEmail(String email) {
        $(byName("email")).setValue(email);
        log.info("email is set");
        return this;
    }

    @Step("Setting '{password}' into password field")
    public SignUpPage setPassword(String password) {
        $(byName("password")).setValue(password);
        log.info("password is set");
        return this;
    }

    @Step("Setting '{mobilephone}' into phone field")
    public SignUpPage setPhoneNumber(String mobilePhone) {
        $(byName("mobilephone")).shouldHave(SelenideCondition.jsLoaded).setValue(mobilePhone);
        log.info("mobilephone is set");
        return this;
    }

    @Step("Confirming arrangements")
    public SignUpPage confirmAgreements() {
        $x("//input[@type='checkbox']").click();
        log.debug("Clicked on 'Confirm Arrangements' checkbox");
        return this;
    }

    public AccountTypePage continueRegister() {
        getWaitOpenPageElement().click();
        log.debug("Clicked on 'CONTINUE' button");
        return new AccountTypePage().waitUntilPageWillOpen();
    }
}
