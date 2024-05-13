package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObj;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

import lombok.experimental.UtilityClass;

public class BaseClass {
	public DataBaseUtility db = new DataBaseUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
    
	public  WebDriver driver = null;
	public static  WebDriver sdriver = null;
    
	@BeforeSuite
	//(groups = { "regressionTest", "smokeTest" })
	public void configBSuite() throws SQLException {
		System.out.println("connect to db");
		db.getDbConnection();
			}

	//@Parameters("BROWSER")--this for cross browser testing
	@BeforeClass      //(groups = { "regressionTest", "smokeTest" })
	public void configBC() throws IOException {
		System.out.println("launch browser");
		String Browser =  flib.getDataFromProperties("browser");          //browser;
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver= driver;
		UtilityClassObj.setdriver(driver);
	}

	@BeforeMethod   //(groups = { "regressionTest", "smokeTest" })
	public void configBM() throws IOException {
		System.out.println("login");
		String URL = flib.getDataFromProperties("url");
		String USERNAME = flib.getDataFromProperties("username");
		String PASSWORD = flib.getDataFromProperties("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod     //(groups = { "regressionTest", "smokeTest" })
	public void configAfterMethd() {
		System.out.println("logout");
		
		HomePage hp = new HomePage(driver);
		hp.logout();

	}

	@AfterClass      //(groups = { "regressionTest", "smokeTest" })
	public void configAfterClass() {
		System.out.println("close browser");
		driver.quit();
	}

	@AfterSuite         //(groups = { "regressionTest", "smokeTest" })
	public void configAterSuite() {
		System.out.println("close db connectn");
		db.closeDBConnection();	
	}

}
