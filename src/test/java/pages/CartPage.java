package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private static final By REMOVE_FROM_THE_CART_BUTTON = By.cssSelector(".cart_button");
    private final static String ITEM_LABEL_LOCATOR = "//div[@class='inventory_item_name' and text()='%s']/ancestor::div[@class='cart_item_label']";
    private final static By ITEM_DESCRIPTION = By.cssSelector(".inventory_item_desc");
    private final static By ITEM_PRICE = By.xpath(".//div[contains(text(),'$')]");
    private final static By ITEM_NAME = By.cssSelector(".inventory_item_name");


    public CartPage(WebDriver driver) {
        super (driver);
    }

    public boolean isCheckOutButtonPresent() {
        try {
            driver.findElement(CHECKOUT_BUTTON);
        } catch (NoSuchElementException e3) {
           return false;
        }
        return true;
    }

    public String getItemDescription(String itemName) {
        return driver.findElement(getItemLabelLocator(itemName)).findElement(ITEM_DESCRIPTION).getText();
    }

    public String getItemPrice (String itemName) {
        return driver.findElement(getItemLabelLocator(itemName)).findElement(ITEM_PRICE).getText();
    }

    public boolean isItemDisplayed(String itemName) {
        try{
            driver.findElement(getItemLabelLocator(itemName)).findElement(ITEM_NAME).isDisplayed();
        } catch (NoSuchElementException e4){
            return false;
        }
            return true;
    }

    public void clickContinueShoppingButton() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickRemoveFromTheCartButton(String itemName) {
        driver.findElement(getItemLabelLocator(itemName)).findElement(REMOVE_FROM_THE_CART_BUTTON).click();
    }

    private By getItemLabelLocator(String itemName) {
        return By.xpath(String.format(ITEM_LABEL_LOCATOR, itemName));
    }





}
