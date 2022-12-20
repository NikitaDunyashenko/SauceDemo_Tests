package tests;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = {"smoke"})
    @Description("checking the possibility to login after entering the correct email and password")
    public void positiveLoginTest() {
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartButtonPresent());
    }

    @Test(groups = {"smoke"})
    @Description("checking the possibility to login after entering the incorrect email and password")
    public void negativeLoginTest() {
        loginPage.setUserName("standard1_user");
        loginPage.setPassword("secret1_sauce");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(), "Epic sadface: Username and password do not match any user in this service");
    }



}
