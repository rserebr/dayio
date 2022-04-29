package com.dayio.automation.ui.pages;

import com.codeborne.selenide.Condition;
import com.dayio.automation.ui.components.AddEmployeeComponent;
import com.dayio.automation.ui.components.NavigationPanelComponent;
import com.dayio.automation.ui.core.BasePage;
import com.dayio.automation.ui.core.utils.SelenideCondition;
import com.dayio.automation.ui.domains.Employee;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$x;

public class EmployeesPage extends BasePage<EmployeesPage> {

    AddEmployeeComponent addEmployeeComponent = new AddEmployeeComponent();
    NavigationPanelComponent navigationPanelComponent = new NavigationPanelComponent();

    public EmployeesPage() {
        super(byXpath("//div[@class='table-page__filters-row']"));
    }

    @Step("Adding single Employee")
    public EmployeesPage selectAddSingleEmployee() {
        $x("//button[@class= 'sc-kTUwUJ fUdXbA']").scrollIntoView(true).click();
        $x("//div[contains(@class, 'sc-dAOnuy')]").shouldBe(Condition.visible).$$x("./div/div").stream()
                .filter(e -> e.getText().equals("One employee")).findFirst().get().click();
        return this;
    }

    @Step("Filling Single Employee details")
    public AddEmployeeComponent fillEmployeeForm(Employee employee) {
         return addEmployeeComponent.setFullName(employee.getFullName())
                .setEmail(employee.getEmail());
    }

    public String getSuccessfulMessageText() {
        return $x("//div[contains(@class, 'notification_success')]").shouldHave(SelenideCondition.jsLoaded).getText();
    }

    public PunchesPage selectPunchesMenu() {
        navigationPanelComponent.selectPunches();
        return new PunchesPage().waitUntilPageWillOpen();
    }
}
