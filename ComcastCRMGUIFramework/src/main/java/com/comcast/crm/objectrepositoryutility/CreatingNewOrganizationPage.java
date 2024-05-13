package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver){ //rule 3 object initialization	
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name = "phone")
	private WebElement phNumEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDropDown;

	@FindBy(name = "accounttype")
	private WebElement typeDropDown;
	
	@FindBy(className  = "dvHeaderText")
	private WebElement orgHeader;
	
	
	public WebElement getOrgHeader() {
		return orgHeader;
	}
	public WebElement getphNumEdt() {
		return phNumEdt;
	}
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}
	public WebElement getTypeDropDown() {
		return typeDropDown;
	}
	//Methods
	public void createphnum(String phn) {
		phNumEdt.sendKeys(phn);
	}
		public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
	}
	
	public void createIndustry(String orgname,String industry,String type) {
		orgNameEdt.sendKeys(orgname);
		Select sle= new Select(industryDropDown);
		sle.selectByVisibleText(industry);
		Select sle1= new Select(typeDropDown);
		sle1.selectByVisibleText(type);
		saveBtn.click();
	}
	
	
}
