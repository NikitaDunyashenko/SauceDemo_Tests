package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private static final By BACK_TO_PRODUCTS_BUTTON = By.id("back-to-products");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBackToProductsButtonPresent() {
        return driver.findElement(BACK_TO_PRODUCTS_BUTTON).isDisplayed();
    }



}
