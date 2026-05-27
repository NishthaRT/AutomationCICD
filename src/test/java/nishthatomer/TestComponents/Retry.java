package nishthatomer.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count =0;
	int maxTry=1;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		//Here we will code if we want to rerun tests again
		if (count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
