package nishthatomer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import nishthatomer.TestComponents.BaseTest;
import nishthatomer.pageobjects.CartPage;
import nishthatomer.pageobjects.CheckoutPage;
import nishthatomer.pageobjects.ConfirmmationPage;
import nishthatomer.pageobjects.OrderPage;
import nishthatomer.pageobjects.ProductsCatalogue;

public class SubmitOrderwithBaseTest extends BaseTest{
	 String productName="ZARA COAT 3";
	   String countryName = "india";
	@Test(dataProvider= "getData",groups={"purchase"})
	   public void submitOrder(HashMap<String,String> input) {
		  
	
		ProductsCatalogue productCatalogue =landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products= productCatalogue.getProductsList();
		productCatalogue.addProducttoCart(input.get("productName"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		List<WebElement> carts = cartPage.verifyCartItem();
		Boolean Match=cartPage.matchProductByName(input.get("productName"));
		
		Assert.assertTrue(Match);
		CheckoutPage checkoutpage=cartPage.goToCheckout();
		checkoutpage.selectCountry(countryName);
		ConfirmmationPage confirmationPage= checkoutpage.submitOrder();
		String message=confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	// Verify Order History
	@Test(dependsOnMethods= {"submitOrder"})
	public void verifyOrderHistory() throws InterruptedException {
		ProductsCatalogue productCatalogue =landingpage.loginApplication("nishtha.tomer@gmail.com", "Abcd@5432");
		OrderPage orderPage= productCatalogue.goToOrderHistory();
		Assert.assertTrue(orderPage.verifyOrderDisplayed(productName));

	}
	
	
	//Data Driven Testing
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "nishtha.tomer@gmail.com");
//		map.put("password", "Abcd@5432");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "nishtha.tomer@gmail.com");
//		map1.put("password", "Abcd@5432");
//		map1.put("productName", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//nishthatomer//data//Purchase.json");
		
		//return new Object[][] {{data.get(0)},{data.get(1)}};
		return new Object[][] {{data.get(0)}};
	}
	
	//Data Provider using Hashmap

}
