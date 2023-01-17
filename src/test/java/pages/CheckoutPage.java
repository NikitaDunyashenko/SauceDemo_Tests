package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

    private static final By FIRST_NAME_INPUT = By.id("first-name");
    private static final By LAST_NAME_INPUT = By.id("last-name");
    private static final By POSTAL_CODE_INPUT = By.id("postal-code");
    private static final By CANCEL_BUTTON  = By.id("cancel");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By PAGE_NAME = By.cssSelector(".title");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isPageNameDisplays();
    }

    public boolean isPageNameDisplays() {
        if (driver.findElement(PAGE_NAME).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void fillingCheckoutForm(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_INPUT).sendKeys(postalCode);
    }

    public void clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public void clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

}
