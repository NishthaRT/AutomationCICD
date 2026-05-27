package nishthatomer.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nishthatomer.reuseableComponents.AbstractComponent;

public class ProductsCatalogue extends AbstractComponent{
	WebDriver driver;
	
	public ProductsCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css="[class*='toast-success']")
	WebElement loginsuccess;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");
	By animation= By.cssSelector("[class*='ng-animating']");
	
	
	public List<WebElement> getProductsList() {
		waitforElementtoDisappear(loginsuccess);
		waitforElementtoAppear(productsBy);
		return products;	
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProducttoCart(String productName) {
		getProductByName(productName).findElement(addToCart).click();
		waitforElementtoAppear(animation);
		waitforElementtoAppear(toastmessage);
	}
	
	
	
}
