package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
WebDriver driver;
public CreatingNewContactPage(WebDriver driver) {
	//this.driver=driver;
	PageFactory.initElements(driver,this);
}

@FindBy(name="lastname")
private WebElement lastNameTF;

@FindBy(xpath = "//input[@title='Save [Alt+S]']")
private WebElement saveBtn;

@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
private WebElement insideplusBtn;

@FindBy(name="search_text")
private WebElement insideSearchTF;

@FindBy(name="search")
private WebElement insideSearchBtn;

@FindBy(name="support_start_date")
private WebElement supportStartDate;

@FindBy(name="support_end_date")
private WebElement supportEndDate;


public WebElement getSupportStartDate() {
	return supportStartDate;
}
public WebElement getSupportEndDate() {
	return supportEndDate;
}
public WebElement getInsideSearchTF() {
	return insideSearchTF;
}
public WebElement getInsideSearchBtn() {
	return insideSearchBtn;
}
public WebElement getInsideplusBtn() {
	return insideplusBtn;	
}
public WebElement getLastNameTF() {
	return lastNameTF;
}
public WebElement getSaveBtn() {
	return saveBtn;
}
public void createContactLastName(String lastName) {
	lastNameTF.sendKeys(lastName);
}
public void sendOrgnameInsideSearchTF(String orgname) {
	insideSearchTF.sendKeys(orgname);
}
}
