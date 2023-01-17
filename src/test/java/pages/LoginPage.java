package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final static By USER_NAME_INPUT = By.cssSelector("#user-name");
    private By passwordInput = By.cssSelector("#password");
    private By loginButton = By.cssSelector("#login-button");
    private By errorMessageContainer = By.cssSelector(".error-message-container");

    public LoginPage(WebDriver driver) {
        super (driver);
    }

    @Override
    public boolean isPageOpened() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void setUserName(String userName) {
        driver.findElement(USER_NAME_INPUT).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessageContainer).getText();
    }

}
