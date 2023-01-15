package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest{

    @Test(groups = {"smoke"})
    @Link("https://www.saucedemo.com/checkout-step-one.html")
    @Story("Story 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("checking if it's possible to fill the checkout form")
    public void settingValuesToCheckoutForm() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());

        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        Assert.assertTrue(productsPage.isRemoveButtonAppears("Sauce Labs Bike Light"));
        productsPage.clickShoppingCartButton();
        Assert.assertTrue(cartPage.isCheckOutButtonPresent());

        cartPage.clickCheckoutButton();
        Assert.assertTrue(checkoutPage.isPageNameDisplays());

        String firstName = "Nikita";
        String lastName = "Dunyashenko";
        int postalCode = 220019;

        checkoutPage.fillingCheckoutForm(firstName, lastName, String.valueOf(postalCode));
        checkoutPage.clickContinueButton();
        Assert.assertTrue(checkoutOverviewPage.isCheckoutOverviewPageNameDisplays());
    }

    @Test(dataProvider = "dataForFillingCheckoutFormNegative", groups = {"smoke"})
    @Link("https://www.saucedemo.com/checkout-step-one.html")
    @Story("Story 4")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("checking if it's possible for user to be redirected to the next page in case of leaving some fields empty")
    public void fillingCheckoutFormNegative(String firstName, String lastName, String postalCode, String errorMessage, String itemName) {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());

        productsPage.clickAddToCartButton(itemName);
        Assert.assertTrue(productsPage.isRemoveButtonAppears(itemName));
        productsPage.clickShoppingCartButton();
        Assert.assertTrue(cartPage.isCheckOutButtonPresent());

        cartPage.clickCheckoutButton();
        Assert.assertTrue(checkoutPage.isPageNameDisplays());

        checkoutPage.fillingCheckoutForm(firstName, lastName, postalCode);
        checkoutPage.clickContinueButton();
        Assert.assertEquals(checkoutPage.getErrorMessage(), errorMessage);


    }

    @Test(groups = {"Regression"})
    @Link("https://www.saucedemo.com/checkout-step-one.html")
    @Story("Story 4")
    @Severity(SeverityLevel.NORMAL)
    @Description("checking if it's possible to go back to shopping cart from the checkout page")
    public void redirectingBackToShoppingCartPage() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());

        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        Assert.assertTrue(productsPage.isRemoveButtonAppears("Sauce Labs Bike Light"));
        productsPage.clickShoppingCartButton();
        Assert.assertTrue(cartPage.isCheckOutButtonPresent());

        cartPage.clickCheckoutButton();
        Assert.assertTrue(checkoutPage.isPageNameDisplays());
        checkoutPage.clickCancelButton();
        Assert.assertTrue(cartPage.isCartPageNameDisplays());
    }

    @DataProvider()
    public Object[][] dataForFillingCheckoutFormNegative() {
        return new Object[][] {
                {"", "", "", "Error: First Name is required", "Sauce Labs Backpack"},
                {"Nikita", "", "", "Error: Last Name is required", "Sauce Labs Bike Light"},
                {"Nikita", "Dunyashenko", "", "Error: Postal Code is required", "Sauce Labs Bolt T-Shirt"},
        };
    }
}
