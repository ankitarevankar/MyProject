package practice.test;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class SampleReportTest {
public ExtentReports report;

	@BeforeSuite
	public void configBS() {
	//spark report config
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM test suite rsults");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
    //add Env info & create test
		    report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}
	
	@AfterSuite
	public void configa() {
		report.flush();	
	
	}	
@Test	
public void createContactTest() throws IOException {
WebDriver driver = new ChromeDriver();
driver.get("http://localhost:8888");
TakesScreenshot ts=(TakesScreenshot)driver;
String filePath =ts.getScreenshotAs(OutputType.BASE64); //base64 support extent report inside filepath we have screenshot locn
ExtentTest test = report.createTest("createContactTest");

test.log(Status.INFO,"login to app");
test.log(Status.INFO,"navigate contat page");
test.log(Status.INFO,"create contact");
if("hdfc".equals("hc")) {
	test.log(Status.PASS, "contact is created");
}else {
	test.addScreenCaptureFromBase64String(filePath, "errfile");
	//test.log(Status.FAIL, "contact is not created");
}
}
@Test
public void createContactWithOrg() {
	
ExtentTest test = report.createTest("createContactWithOrgTest");
test.log(Status.INFO,"login to app");
test.log(Status.INFO,"navigate contat page");
test.log(Status.INFO,"create contact");
if("hdfc".equals("hdfc")) {
	test.log(Status.PASS, "contact is created");
}else {
	test.log(Status.FAIL, "contact is not created");
}
}

}
