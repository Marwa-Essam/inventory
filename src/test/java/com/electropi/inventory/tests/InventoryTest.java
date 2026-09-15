package com.electropi.inventory.tests;

import com.electropi.inventory.base.BaseTest;
import com.electropi.inventory.pages.DashboardPage;
import com.electropi.inventory.pages.InventoryPage;
import com.electropi.inventory.pages.LoginPage;
import com.electropi.inventory.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    @Test(enabled = false)
    public void adminCanAddInventoryItem() {

        String username = System.getenv("STORE_ADMIN_USERNAME");
        String password = System.getenv("STORE_ADMIN_PASSWORD");
        String baseUrl = ConfigReader.getProperty("base.url");
        Assert.assertNotNull(username, "STORE_ADMIN_USERNAME is not configured");
        Assert.assertNotNull(password, "STORE_ADMIN_PASSWORD is not configured");
        Assert.assertNotNull(baseUrl, "BASE_URL is not configured");

        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage();

        InventoryPage inventoryPage = dashboardPage.openInventory();

        inventoryPage.addProduct("Wireless Mouse", "25.00");

        Assert.assertTrue(
                inventoryPage.isSuccessToastDisplayed(),
                "Success message was not displayed"
        );
    }
}