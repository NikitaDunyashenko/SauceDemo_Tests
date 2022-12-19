import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SwagLabs {

    int productNumber = 2;

    @Test
    public void swagLabsTests() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.id("user-name"));
        login.sendKeys("standard_user");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement productsLogo = driver.findElement(By.cssSelector(".title"));
        Assert.assertEquals(productsLogo.isDisplayed(), true);

        List <WebElement> productName = driver.findElements(By.cssSelector(".inventory_item_name"));
        String productName1 = productName.get(productNumber).getText();

        List <WebElement> productPrice = driver.findElements(By.cssSelector(".inventory_item_price"));
        String productPrice1 = productPrice.get(productNumber).getText();

        List <WebElement> products = driver.findElements(By.cssSelector("[class*=btn]"));
        WebElement product1 = products.get(productNumber);
        product1.click();

        String removeFromTheCartButton = driver.findElement(By.xpath("//button[text()='Remove']")).getText();
        Assert.assertEquals(removeFromTheCartButton, "REMOVE");

        WebElement shoppingCart = driver.findElement(By.cssSelector("[class=shopping_cart_container]"));
        shoppingCart.click();

        WebElement checkoutButton = driver.findElement(By.cssSelector("#checkout"));
        Assert.assertEquals(checkoutButton.isDisplayed(), true);

        String productPriceInShoppingCart = driver.findElement(By.cssSelector(".inventory_item_price")).getText();
        Assert.assertEquals(productPriceInShoppingCart, productPrice1);

        String productNameInShoppingCart = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        Assert.assertEquals(productNameInShoppingCart, productName1);

        driver.quit();
    }
}
