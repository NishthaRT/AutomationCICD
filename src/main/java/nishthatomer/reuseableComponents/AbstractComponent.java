package nishthatomer.reuseableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nishthatomer.pageobjects.CartPage;
import nishthatomer.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver1) {
		// TODO Auto-generated constructor stub
		this.driver=driver1;
		PageFactory.initElements(driver1,this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartheader;

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement orderheader;
	
	public void waitforElementtoDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void waitforElementtoAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitforWebElementtoAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(findBy)); 
	}
	
	public CartPage goToCartPage() {
		cartheader.click();
		CartPage cartPage= new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderHistory() {
		orderheader.click();
		OrderPage orders=new OrderPage(driver);
		return orders;
	}
	

}
