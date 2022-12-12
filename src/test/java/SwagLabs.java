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

        List <WebElement> products = driver.findElements(By.cssSelector("[class*=btn]"));
        WebElement product1 = products.get(0);
        product1.click();

        String productDescriptionOnTheMainPage = driver.findElement(By.xpath("//div/div[text()='carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.']")).getText();
        String productPriceOnTheMainPage = driver.findElement(By.xpath("//div/div[text()='29.99']")).getText();

        WebElement shoppingCart = driver.findElement(By.cssSelector("[class=shopping_cart_container]"));
        shoppingCart.click();

        WebElement checkoutButton = driver.findElement(By.cssSelector("#checkout"));
        Assert.assertEquals(checkoutButton.isDisplayed(), true);

        WebElement productPriceInShoppingCart1 = driver.findElement(By.xpath("//div/div[text()='29.99']"));
        Assert.assertEquals(productPriceInShoppingCart1.getText(), productPriceOnTheMainPage);

        WebElement productDescriptionInShoppingCart1 = driver.findElement(By.xpath("//div/div[text()='carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.']"));
        Assert.assertEquals(productDescriptionInShoppingCart1.getText(), productDescriptionOnTheMainPage);

        driver.quit();
    }
}
