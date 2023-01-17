package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage{

    private static final By CHECKOUT_OVERVIEW_PAGE_NAME = By.cssSelector(".title");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isCheckoutOverviewPageNameDisplays();
    }

    public boolean isCheckoutOverviewPageNameDisplays() {
        if (driver.findElement(CHECKOUT_OVERVIEW_PAGE_NAME).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
