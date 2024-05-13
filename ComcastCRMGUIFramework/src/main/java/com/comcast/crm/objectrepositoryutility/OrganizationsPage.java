package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver;
	public OrganizationsPage(WebDriver driver){ //rule 3 object initialization	
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath ="//img[@title='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchTF;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchNowBtn;
	
	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
    public WebElement getSearchTF() {
		return searchTF;
	}
	public WebElement getSearchDD() {
		return searchDD;
	}
	
	
	
	
}
