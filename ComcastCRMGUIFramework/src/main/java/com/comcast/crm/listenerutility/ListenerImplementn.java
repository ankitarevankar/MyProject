package com.comcast.crm.listenerutility;

import java.awt.ItemSelectable;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.BaseClassFinder;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObj;


public class ListenerImplementn implements ITestListener,ISuiteListener {
public ExtentReports report;
public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("report congfig");
		// spark report config
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
        ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_" + time + ".html");
		spark.config().setDocumentTitle("CRM test suite rsults");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		// add Env info & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("report backup");
		report.flush();         //this will storing result
		
	
	}

	@Override
	public void onTestStart(ITestResult result) {
	System.out.println(result.getMethod().getMethodName()+"----start-----");
    test = report.createTest(result.getMethod().getMethodName());
   
    UtilityClassObj.setTest(test);
    test.log(Status.INFO,result.getMethod().getMethodName()+ "started" );
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+"----end-----");
		 test.log(Status.PASS,result.getMethod().getMethodName()+ "completed" );
			
	}

	@Override
	public void onTestFailure(ITestResult result){
	  String testName =	result.getMethod().getMethodName();
	  TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
	  String srcFile = ts.getScreenshotAs(OutputType.BASE64);
	//  String time=LocalDateTime.now().toString().replace(':', '_');
	 String time= new Date().toString().replace(" ", "_").replace(":", "_");
	 test.addScreenCaptureFromBase64String(srcFile, testName+"_"+time);
	 test.log(Status.FAIL,result.getMethod().getMethodName()+"failed" );
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 test.log(Status.INFO,result.getMethod().getMethodName()+"skipped" );
			
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

	
	
}
