package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
WebDriver driver;
public  ContactInfoPage(WebDriver driver) {
this.driver= driver;
PageFactory.initElements(driver,this);
}

@FindBy(id="dtlview_Last Name")
private WebElement headerContact;

@FindBy(id="dtlview_Support Start Date")
private WebElement supportStartDateView;

public WebElement getSupportStartDateView() {
	return supportStartDateView;
}


public WebElement getSupportEndDateView() {
	return supportEndDateView;
}

@FindBy(id="dtlview_Support End Date")
private WebElement supportEndDateView;


public WebElement getHeaderContact() {
	return headerContact;
}

}
