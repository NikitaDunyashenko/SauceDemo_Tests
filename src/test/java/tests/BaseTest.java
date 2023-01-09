package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected ProductDetailsPage productDetailsPage;
    protected CheckoutPage checkoutPage;
    protected CheckoutOverviewPage checkoutOverviewPage;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true, description = "Setting up the driver")
    public void setUp(String browserName, ITestContext testContext) {

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browserName.equals("safari")){
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        testContext.setAttribute("driver", driver);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
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
