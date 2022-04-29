package com.dayio.automation.ui.pages;

import com.dayio.automation.ui.core.BasePage;
import com.dayio.automation.ui.core.properties.EnvProperties;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class LoginPage extends BasePage<LoginPage> {

    private static final EnvProperties ENV_PROPS = ConfigFactory.create(EnvProperties.class);


    public LoginPage() {
        super(ENV_PROPS.getDevEnvironmentUrl(), byName("email"));
    }

    @Step("User started registration by clicking Get Started button")
    public SignUpPage registerUser() {
        $x("//div[text()='Get started']").click();
        return new SignUpPage().waitUntilPageWillOpen();
    }
}
