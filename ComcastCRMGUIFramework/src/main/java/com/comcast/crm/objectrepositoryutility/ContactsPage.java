package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	WebDriver driver;
	public ContactsPage(WebDriver driver){ //rule 3 object initialization	
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
		
	@FindBy(xpath ="//img[@title='Create Contact...']")
	private WebElement createNewContactBtn;
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement lastName;
	
	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
	}
	
}
