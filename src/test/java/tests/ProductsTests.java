package tests;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductsTests extends BaseTest{

    @Test(dataProvider = "productNameData", groups = {"smoke"}, retryAnalyzer = RetryAnalyzer.class)
    @Description("checking if it's possible to add all products to the cart from the main page")
    public void addProductToTheCart(String productName) {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
        productsPage.clickAddToCartButton(productName);
        Assert.assertTrue(productsPage.isRemoveButtonAppears(productName));
    }

    @Test(dataProvider = "productNameData", groups = {"smoke"})
    @Description("checking if it's possible to remove the product from the cart when it has been already added")
    public void removeProductFromTheCart(String productName) {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
        productsPage.clickAddToCartButton(productName);
        Assert.assertTrue(productsPage.isRemoveButtonAppears(productName));
        productsPage.clickRemoveFromTheCartButton(productName);
        Assert.assertTrue(productsPage.isAddToCartButtonAppears(productName));
    }

    @Test(groups = {"Regression"})
    @Description("checking if it's possible to be redirected to the shopping cart page after adding a product to the cart")
    public void redirectingToShoppingCart() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        Assert.assertTrue(productsPage.isRemoveButtonAppears("Sauce Labs Bike Light"));
        productsPage.clickShoppingCartButton();
        Assert.assertTrue(cartPage.isCheckOutButtonPresent());
    }

    @Test(groups = {"slow"})
    @Description("checking if it's possible to redirect on the page with product details")
    public void openItemCard() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
        productsPage.openItem("Sauce Labs Bike Light");
        Assert.assertTrue(productDetailsPage.isBackToProductsButtonPresent());
    }

    @DataProvider()
    public Object[][] productNameData() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"},
                {"Sauce Labs Onesie"},
                {"Test.allTheThings() T-Shirt (Red)"},
        };
    }


}
