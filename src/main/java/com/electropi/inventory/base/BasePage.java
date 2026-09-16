package com.electropi.inventory.base;

import com.electropi.inventory.factory.DriverFactory;
import com.electropi.inventory.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.waitUtils = new WaitUtils(driver);
    }

    protected void click(By locator) {
        waitUtils.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        waitUtils.waitForVisible(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return waitUtils.waitForVisible(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        return waitUtils.waitForDisplayed(locator);
    }

    protected void waitForElementToDisappear(By locator) {
        waitUtils.waitForInvisible(locator);
    }
}
