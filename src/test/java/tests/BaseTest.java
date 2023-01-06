package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected ProductDetailsPage productDetailsPage;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true, description = "Setting up the driver")
    public void setUp(String browserName) {

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browserName.equals("safari")){
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @BeforeMethod(alwaysRun = true, description = "Website URL")
    public void navigate() {
        driver.get("https://www.saucedemo.com/");
    }

    @AfterClass(alwaysRun = true, description = "Quiting the website after each class")
    public void tearDown() {
        driver.quit();
    }

}
