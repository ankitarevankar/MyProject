package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class AutohealingExample {

	@FindBy(name="user_name")
	private WebElement unEdit;
		
	@FindBy(name="user_password")
	private WebElement pwEdit;
	
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath="//input[@type='sumit']")}) //ex for autohealing
      private WebElement loginbtn;

	
@Test	
public void sampleTest() {
WebDriver driver= new ChromeDriver();
driver.get("http://localhost:8888");
AutohealingExample s=PageFactory.initElements(driver,AutohealingExample.class);
s.unEdit.sendKeys("admin");
s.pwEdit.sendKeys("admin");
s.loginbtn.click();
}

	
	
}
