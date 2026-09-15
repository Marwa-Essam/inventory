package com.electropi.inventory.pages;

import com.electropi.inventory.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("loginBtn");

    public LoginPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}