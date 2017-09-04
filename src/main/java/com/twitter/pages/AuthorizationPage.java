package com.twitter.pages;

import com.twitter.models.User;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Assert.assertTrue;

public class AuthorizationPage extends BasePage {

    public static final String URL = baseUrl;
    // Sign in block
    private static final By FORM_SIGN_IN = By.xpath("//div[@class='front-signin js-front-signin']");
    private static final By FIELD_LOGIN = By.xpath("//input[@id='signin-email'][@autocomplete='username']");
    private static final By FIELD_PASSWORD = By.xpath("//input[@id='signin-password'][@autocomplete='current-password']");
    private static final By BUTTON_LOG_IN = By.xpath("//button[@type='submit'][contains(text(),'Log in')]");


    @Override
    public boolean isLoaded() {
        return isVisible(FORM_SIGN_IN);
    }

    @Override
    public AuthorizationPage ensureLoaded() {
        assertTrue(isLoaded());
        return this;
    }

    // Actions

    @Step("Set login")
    private void setLoginField(String login) {
        input(FIELD_LOGIN, login);
    }

    @Step("Set password")
    private void setPasswordField(String password) {
        input(FIELD_PASSWORD, password);
    }

    @Step("Click 'Log in' button")
    private void clickLogInButton() {
        click(BUTTON_LOG_IN);
    }

    // Scenarios

    @Step("Sign in as")
    public void signIn(User user) {
        setLoginField(user.getEmail());
        setPasswordField(user.getPassword());
        clickLogInButton();
    }
}
