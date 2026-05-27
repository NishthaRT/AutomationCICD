package nishthatomer;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import nishthatomer.TestComponents.BaseTest;
import nishthatomer.TestComponents.Retry;

public class ErrorValidation extends BaseTest {
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void logineErrorValidation(){
		landingpage.loginApplication("nishtha.tomer@gmail.com", "@5432");
		AssertJUnit.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());

	}

}
