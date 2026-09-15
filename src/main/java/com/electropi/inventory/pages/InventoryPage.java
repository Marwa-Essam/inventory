package com.electropi.inventory.pages;

import com.electropi.inventory.base.BasePage;
import org.openqa.selenium.By;

public class InventoryPage extends BasePage {

    private final By productNameField = By.id("productName");
    private final By priceField = By.id("price");
    private final By saveButton = By.id("saveBtn");
    private final By successToast = By.cssSelector(".toast-success");

    public InventoryPage enterProductName(String productName) {
        type(productNameField, productName);
        return this;
    }

    public InventoryPage enterPrice(String price) {
        type(priceField, price);
        return this;
    }

    public InventoryPage clickSave() {
        click(saveButton);
        return this;
    }

    public boolean isSuccessToastDisplayed() {
        return isDisplayed(successToast);
    }

    public void addProduct(String productName, String price) {
        enterProductName(productName);
        enterPrice(price);
        clickSave();
    }
}