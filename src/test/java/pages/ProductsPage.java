package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{

    private final static By SHOPPING_CART_BUTTON = By.cssSelector(".shopping_cart_link");
    private final static String ITEM_CONTAINER_LOCATOR = "//div[@class='inventory_item_name' and text()='%s']/ancestor::div[@class='inventory_item']";
    private final static By ADD_TO_CART_BUTTON = By.xpath("//button[text()='Add to cart']");
    private final static By REMOVE_FROM_THE_CART_BUTTON = By.xpath("//button[text()='Remove']");

    public ProductsPage(WebDriver driver) {
        super (driver);
    }

    public boolean isShoppingCartButtonPresent() {
        try {
            driver.findElement(SHOPPING_CART_BUTTON);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void clickShoppingCartButton() {
        driver.findElement(SHOPPING_CART_BUTTON).click();
    }

    public void clickAddToCartButton(String itemName) {
        driver.findElement(getItemContainerByName(itemName)).findElement(ADD_TO_CART_BUTTON).click();
    }

    public void clickRemoveFromTheCartButton(String itemName) {
        driver.findElement(getItemContainerByName(itemName)).findElement(REMOVE_FROM_THE_CART_BUTTON).click();
    }

    public boolean isRemoveButtonAppears() {
        try{
            driver.findElement(REMOVE_FROM_THE_CART_BUTTON);
        } catch (NoSuchElementException e1) {
            return false;
        }
        return true;
    }

    public boolean isAddToCartButtonAppears() {
        try{
            driver.findElement(ADD_TO_CART_BUTTON);
        } catch (NoSuchElementException e2) {
            return false;
        }
        return true;
    }

    public String getItemPrice(String itemName) {
        return driver.findElement(getItemContainerByName(itemName)).findElement(By.xpath("//div[contains(text(),'$')]")).getText();
    }

    public String getItemDescription(String itemName) {
        return driver.findElement(getItemContainerByName(itemName)).findElement(By.cssSelector(".inventory_item_desc")).getText();
    }

    public void openItem(String itemName) {
        driver.findElement(getItemContainerByName(itemName)).findElement(By.xpath("//img[@class='inventory_item_img']")).click();
    }

    private By getItemContainerByName(String itemName) {
        return By.xpath(String.format(ITEM_CONTAINER_LOCATOR, itemName));
    }


}
