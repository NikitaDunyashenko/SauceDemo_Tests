package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory extends BasePage{

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    public LoginPageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        return loginButton.isDisplayed();
    }

    public LoginPageFactory setUserNameInput(String userName) {
        userNameInput.sendKeys(userName);
        return this;
    }

    public LoginPageFactory setPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public ProductsPage clickLogin() {
        loginButton.click();
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
