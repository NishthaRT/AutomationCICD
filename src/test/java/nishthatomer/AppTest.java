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
import nishthatomer.pageobjects.LandingPage;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
   public static void main(String[] args) {
	   String productName="ZARA COAT 3";
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	LandingPage landingpage= new LandingPage(driver);
	driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	
	driver.findElement(By.cssSelector("#userEmail")).sendKeys("nishtha.tomer@gmail.com");
	driver.findElement(By.cssSelector("#userPassword")).sendKeys("Abcd@5432");
	driver.findElement(By.cssSelector("#login")).click();
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".mb-3"))));
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	List<WebElement> carts = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean Match = carts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(Match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions action = new Actions(driver);
	action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	String message= driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	driver.close();
}
}
