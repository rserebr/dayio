package com.dayio.automation.ui.components;

import com.dayio.automation.ui.pages.EmployeesPage;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AddEmployeeComponent {

    public AddEmployeeComponent setFullName(String fullName) {
        log.info("Setting employee name as " + fullName);
        $x("//div[text()='Full name']/following-sibling::div//input").setValue(fullName);
        return this;
    }

    public AddEmployeeComponent setEmail(String email) {
        log.info("Setting email as " + email);
        $x("//input[@type='email']").setValue(email);
        return this;
    }

    public EmployeesPage clickAddEmployeeButton() {
        log.info("Clicking add employee button ");
        $x("//button[@state='primary']").click();
        return new EmployeesPage().waitUntilPageWillOpen();
    }

}
