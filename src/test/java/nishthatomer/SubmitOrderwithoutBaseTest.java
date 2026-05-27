package nishthatomer;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import nishthatomer.pageobjects.CartPage;
import nishthatomer.pageobjects.CheckoutPage;
import nishthatomer.pageobjects.ConfirmmationPage;
import nishthatomer.pageobjects.LandingPage;
import nishthatomer.pageobjects.ProductsCatalogue;

/**
 * Unit test for simple App.
 */
public class SubmitOrderwithoutBaseTest {

    /**
     * Rigorous Test :-)
     */
   public static void main(String[] args) {
	   String productName="ZARA COAT 3";
	   String countryName = "india";
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	LandingPage landingpage= new LandingPage(driver);
	landingpage.goTo();
	ProductsCatalogue productCatalogue =landingpage.loginApplication("nishtha.tomer@gmail.com", "Abcd@5432");
	List<WebElement> products= productCatalogue.getProductsList();
	productCatalogue.addProducttoCart(productName);
	CartPage cartPage=productCatalogue.goToCartPage();
	
	List<WebElement> carts = cartPage.verifyCartItem();
	Boolean Match=cartPage.matchProductByName(productName);
	
	Assert.assertTrue(Match);
	CheckoutPage checkoutpage=cartPage.goToCheckout();
	checkoutpage.selectCountry(countryName);
	ConfirmmationPage confirmationPage= checkoutpage.submitOrder();
	String message=confirmationPage.getConfirmationMessage();
	Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	driver.close();
}
}
