package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObj //this class helps to share static variable
{
public static ThreadLocal<ExtentTest> test= new ThreadLocal<ExtentTest>();  
public static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();

public static ExtentTest getTest() {
	return test.get();
}
public static void setTest(ExtentTest actTest) {
	test.set(actTest);
}
public static WebDriver getDriver() {
	return driver.get();
}
public static void setdriver(WebDriver actdriver) {
	driver.set(actdriver);
}
}
