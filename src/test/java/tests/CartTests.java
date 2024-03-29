package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CartTests extends BaseTest{

    @Test(dataProvider = "productNameData", groups = {"slow"})
    @Link("https://www.saucedemo.com/cart.html")
    @Story("Story 3")
    @Severity(SeverityLevel.CRITICAL)
    @Description("checking the sameness of products' price and products' description on main page and shopping cart page")
    public void validationOfAddedProductToCart(String productName) {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isPageOpened());

        productsPage.clickAddToCartButton(productName);
        String productDescriptionOnMainPage = productsPage.getItemDescription(productName);
        String productPriceOnMainPage = productsPage.getItemPrice(productName);
        productsPage.clickShoppingCartButton();

        String productPriceInShoppingCart = cartPage.getItemPrice(productName);
        String productDescriptionInShoppingCart = cartPage.getItemDescription(productName);
        Assert.assertEquals(productDescriptionOnMainPage, productDescriptionInShoppingCart);
        Assert.assertEquals(productPriceOnMainPage, productPriceInShoppingCart);
    }


    @Test(dataProvider = "productNameData", groups = {"smoke"})
    @Link("https://www.saucedemo.com/cart.html")
    @Story("Story 3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("checking if added to the cart product is possible to remove from shopping cart")
    public void removeProductFromTheCart(String productName) {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isPageOpened());

        productsPage.clickAddToCartButton(productName);
        String productDescriptionOnMainPage = productsPage.getItemDescription(productName);
        String productPriceOnMainPage = productsPage.getItemPrice(productName);
        productsPage.clickShoppingCartButton();

        String productPriceInShoppingCart = cartPage.getItemPrice(productName);
        String productDescriptionInShoppingCart = cartPage.getItemDescription(productName);
        Assert.assertEquals(productDescriptionOnMainPage, productDescriptionInShoppingCart);
        Assert.assertEquals(productPriceOnMainPage, productPriceInShoppingCart);

        cartPage.clickRemoveFromTheCartButton(productName);

        Assert.assertEquals(cartPage.isItemDisplayed(productName), false);
    }

    @Test(dataProvider = "productNameData", groups = {"slow"})
    @Link("https://www.saucedemo.com/cart.html")
    @Story("Story 3")
    @Severity(SeverityLevel.NORMAL)
    @Description("checking if it's possible to continue shopping when the product has already been added to the cart")
    public void redirectingToProductPage(String productName) {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isPageOpened());

        productsPage.clickAddToCartButton(productName);
        String productDescriptionOnMainPage = productsPage.getItemDescription(productName);
        String productPriceOnMainPage = productsPage.getItemPrice(productName);
        productsPage.clickShoppingCartButton();

        String productPriceInShoppingCart = cartPage.getItemPrice(productName);
        String productDescriptionInShoppingCart = cartPage.getItemDescription(productName);
        Assert.assertEquals(productDescriptionOnMainPage, productDescriptionInShoppingCart);
        Assert.assertEquals(productPriceOnMainPage, productPriceInShoppingCart);

        cartPage.clickContinueShoppingButton();
        Assert.assertEquals(productsPage.isRemoveButtonAppears(productName), true);

    }

    @Test(groups = {"smoke"})
    @Link("https://www.saucedemo.com/cart.html")
    @Story("Story 3")
    @Severity(SeverityLevel.NORMAL)
    @Description("checking if it's possible to redirect user to checkout page")
    public void redirectingToCheckoutPage() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isPageOpened());

        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        Assert.assertTrue(productsPage.isRemoveButtonAppears("Sauce Labs Bike Light"));
        productsPage.clickShoppingCartButton();
        Assert.assertTrue(cartPage.isPageOpened());

        cartPage.clickCheckoutButton();
        Assert.assertTrue(checkoutPage.isPageOpened());

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
