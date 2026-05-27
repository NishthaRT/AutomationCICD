package nishthatomer.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports config() {
		String path = System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation results");
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tseter", "Nishtha");
		return extent;
	}

}
