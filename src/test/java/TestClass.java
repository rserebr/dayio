import com.dayio.automation.ui.core.asserts.SoftAssert;
import com.dayio.automation.ui.core.configurators.BaseTest;
import com.dayio.automation.ui.domains.AdminUser;
import com.dayio.automation.ui.domains.Company;
import com.dayio.automation.ui.domains.Employee;
import com.dayio.automation.ui.enums.NumberEmployees;
import com.dayio.automation.ui.enums.PunchReason;
import com.dayio.automation.ui.enums.PunchType;
import com.dayio.automation.ui.pages.PunchesPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import com.dayio.automation.ui.pages.AccountTypePage;
import com.dayio.automation.ui.pages.EmployeesPage;
import com.dayio.automation.ui.pages.InviteEmplyeesPage;
import com.dayio.automation.ui.pages.LoginPage;


public class TestClass extends BaseTest {
    LoginPage loginPage = new LoginPage();
    AccountTypePage accountTypePage;
    InviteEmplyeesPage inviteEmployeesPage;
    EmployeesPage employeesPage;

    @Test
    public void testOne() {

        AdminUser adminUser = AdminUser.fill()
                .fullName(RandomStringUtils.randomAlphabetic(8))
                .email(RandomStringUtils.randomAlphabetic(8)+"@mail.com")
                .password(RandomStringUtils.randomAlphabetic(6)
                        + RandomStringUtils.randomNumeric(2)
                        + RandomStringUtils.random(6, 33, 47, false, false))
                .mobilePhone(RandomStringUtils.randomNumeric(7))
                .create();

        Employee employee = Employee.fill()
                .fullName(RandomStringUtils.randomAlphabetic(8))
                .email(RandomStringUtils.randomAlphabetic(8)+"@mail.com")
                .create();

        Company company = Company.fill()
                .companyName(RandomStringUtils.randomAlphabetic(8))
                .address("Kiev")
                .category("Automotive Services")
                .numberEmployees(NumberEmployees.FIFTY_TO_NINETY_NINE)
                .create();

        accountTypePage = loginPage.openPage()
                .registerUser()
                .fillRegisterForm(adminUser)
                .confirmAgreements()
                .continueRegister();

        inviteEmployeesPage = accountTypePage.fillCompanyRegisterForm(company)
                .continueRegister();

        employeesPage = inviteEmployeesPage.clickSkipButton()
                .selectAddSingleEmployee()
                .fillEmployeeForm(employee)
                .clickAddEmployeeButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(employeesPage.getSuccessfulMessageText(), "111You've added a new employee sucessfully");

        PunchesPage punchesPage = employeesPage.selectPunchesMenu()
                .clickAddPunchAdjustment()
                .selectEmployee(employee.getFullName())
                .selectRequiredDate(-1)
                .selectRequiredTime("1200")
                .selectPunchType(PunchType.ENTRY)
                .selectPunchLocation(company.getCompanyName())
                .selectPunchReason(PunchReason.EARLY_LEAVE)
                .confirmPunch();

        softAssert.assertEquals(punchesPage.getSuccessfulMessageText(), "You added a punch to " + employee.getFullName());
        softAssert.assertAll();
    }
}
