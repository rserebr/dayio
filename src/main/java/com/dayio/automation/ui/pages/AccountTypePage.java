package com.dayio.automation.ui.pages;

import com.dayio.automation.ui.core.BasePage;
import com.dayio.automation.ui.domains.Company;
import com.dayio.automation.ui.enums.NumberEmployees;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AccountTypePage extends BasePage<AccountTypePage> {
    protected AccountTypePage() {
        super(byXpath("//button[text()='Continue']"));
    }

    @Step("Filling company registration form")
    public AccountTypePage fillCompanyRegisterForm(Company company) {
        setCompanyName(company.getCompanyName());
        setAddress(company.getAddress());
        setCategory(company.getCategory());
        selectNumberEmployees(company.getNumberEmployees());
        return this;
    }

    @Step("Setting Company name as '{companyName}'")
    public AccountTypePage setCompanyName(String companyName) {
        $(byName("company")).setValue(companyName);
        log.info("Company name is set");
        return this;
    }

    @Step("Setting Address as '{address}'")
    public AccountTypePage setAddress(String address) {
        $x("//input[@role='combobox']").setValue(address);
        $$(byXpath("//*[@class='autocomplete-dropdown-container expanded']/div")).first().click();
        log.info("Company address is set");
        return this;
    }

    @Step("Setting Category as '{category}'")
    public AccountTypePage setCategory(String category) {
        $x("//div[contains(@class, 'multi-select')]").click();
        $$(byXpath("//*[contains(@class, 'react-select__menu')]/div/div")).stream()
                .filter(e -> e.getText().equals(category))
                .findFirst().get().click();
        log.info("Company category is set");
        return this;
    }

    @Step("Selecting number of employees as '{numberEmployees}'")
    public AccountTypePage selectNumberEmployees(NumberEmployees numberEmployees) {
        $$x("//div[contains(@class, 'sc-hGKTXy GkVIO')]").stream()
                .filter(e -> e.getText().equals(numberEmployees.getValue()))
                .findFirst().get().click();
        log.info("Company number of employees is set");
        return this;
    }

    public InviteEmplyeesPage continueRegister() {
        getWaitOpenPageElement().click();
        log.debug("Clicked on 'CONTINUE' button");
        return new InviteEmplyeesPage().waitUntilPageWillOpen();
    }

}
