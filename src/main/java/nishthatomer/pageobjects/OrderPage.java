package nishthatomer.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import nishthatomer.reuseableComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
	public  OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orders;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public List<WebElement> verifyCartItem() {
		return orders;
	}
	
	public Boolean verifyOrderDisplayed(String productName) {
		Boolean Match = orders.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
		return Match;
	}
	
	public CheckoutPage goToCheckout() {
		checkout.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
}
