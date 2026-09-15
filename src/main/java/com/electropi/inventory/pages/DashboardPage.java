package com.electropi.inventory.pages;

import com.electropi.inventory.base.BasePage;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {

    private final By inventoryLink = By.linkText("Inventory");

    public InventoryPage openInventory() {
        click(inventoryLink);
        return new InventoryPage();
    }
}