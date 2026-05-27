package nishthatomer.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nishthatomer.reuseableComponents.AbstractComponent;

/**
 * Hello world!
 */
public class LandingPage extends AbstractComponent {
 
		WebDriver driver;
		
		public LandingPage(WebDriver driver1){
			super(driver1);
			this.driver= driver1;
			PageFactory.initElements(driver1, this);
		
	}
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement userPassword;
		
		@FindBy(id="login")
		WebElement login;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
		
		
		
		By message = By.cssSelector("[class*='flyInOut']");
		
		public ProductsCatalogue loginApplication(String email, String password) {
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			login.click();
			waitforElementtoAppear(message);
			
			ProductsCatalogue productCatalogue = new ProductsCatalogue(driver);
			return productCatalogue;
		}
		
		public void goTo() {
			driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		}
		
		public String getErrorMessage() {
			waitforWebElementtoAppear(errorMessage);
			String error=errorMessage.getText();
			return error;
			
		}
		
}

