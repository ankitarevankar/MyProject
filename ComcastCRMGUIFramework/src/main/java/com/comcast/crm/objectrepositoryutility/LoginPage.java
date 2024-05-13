package com.comcast.crm.objectrepositoryutility;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * @author Admin
 * contains login page elements & business library like login()
 * 
 */
public class LoginPage extends WebDriverUtility{   //rule 1 create pom class for login page
                           
	WebDriver driver;
	public LoginPage(WebDriver driver){ //rule 3 object initialization	
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//rule 2 objet creation
	@FindBy(name="user_name")
	private WebElement unEdit;
	
	@FindBy(name="user_password")
	private WebElement pwEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;
	
	
//	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath="//input[@value='Login']")}) //ex for autohealing
//	private WebElement loginbtn;

	//rule 4 obj encapsultn using getter
	public WebElement getUnEdit() {
		return unEdit;
	}

	public WebElement getPwEdit() {
		return pwEdit;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
/**
 * login to app based on uname  pwd url args	 
 * @param url
 * @param uname
 * @param pwd
 */
public void loginToApp(String url,String uname, String pwd) {
	waitForPageToLoad(driver);
	driver.manage().window().maximize();
	driver.get(url);
	unEdit.sendKeys(uname);
	pwEdit.sendKeys(pwd);
	loginbtn.submit();	
}	
}
