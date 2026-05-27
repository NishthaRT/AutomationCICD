package nishthatomer.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import nishthatomer.reuseableComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	public  CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> carts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public List<WebElement> verifyCartItem() {
		return carts;
	}
	
	public Boolean matchProductByName(String productName) {
		Boolean Match = carts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		return Match;
	}
	
	public CheckoutPage goToCheckout() {
		checkout.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
}
