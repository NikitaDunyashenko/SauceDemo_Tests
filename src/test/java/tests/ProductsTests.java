package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTests extends BaseTest{

    @Test
    public void addProductToTheCart() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        Assert.assertTrue(productsPage.isRemoveButtonAppears());
    }

    @Test
    public void removeFromTheCart() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        Assert.assertTrue(productsPage.isRemoveButtonAppears());
        productsPage.clickRemoveFromTheCartButton("Sauce Labs Bike Light");
        Assert.assertTrue(productsPage.isAddToCartButtonAppears());
    }

    @Test
    public void redirectingToShoppingCart() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        Assert.assertTrue(productsPage.isRemoveButtonAppears());
        productsPage.clickShoppingCartButton();
        Assert.assertTrue(cartPage.isCheckOutButtonPresent());
    }

    @Test
    public void openItemCard() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
        productsPage.openItem("Sauce Labs Bike Light");
        Assert.assertTrue(productDetailsPage.isBackToProductsButtonPresent());
    }

}
