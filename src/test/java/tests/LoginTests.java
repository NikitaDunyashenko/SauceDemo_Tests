package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = {"smoke"})
    @Link("https://www.saucedemo.com/")
    @Story("Story 1")
    @Severity(SeverityLevel.BLOCKER)
    @Description("checking the possibility to login after entering the correct email and password")
    public void positiveLoginTest() {
        boolean isShoppingCartButton = loginPageFactory.setUserNameInput("standard_user")
                .setPasswordInput("secret_sauce")
                .clickLogin()
                .isShoppingCartButtonPresent();
        Assert.assertTrue(isShoppingCartButton);
    }

    @Test(groups = {"smoke"})
    @Link("https://www.saucedemo.com/")
    @Story("Story 1")
    @Severity(SeverityLevel.CRITICAL)
    @Description("checking the possibility to login after entering the incorrect email and password")
    public void negativeLoginTest() {
        loginPageFactory.setUserNameInput("qwe123")
                .setPasswordInput("123qwe")
                .clickLogin();
        Assert.assertEquals(loginPageFactory.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }



}
