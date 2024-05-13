package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver){ //rule 3 object initialization	
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement actOrgName;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phoneNo;
	
	@FindBy(id="dtlview_Industry")
	private WebElement headerIndustry;
	
	@FindBy(id="dtlview_Type")
	private WebElement headerType;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement OrgTF;
	
	
	
	public WebElement getOrgTF() {
		return OrgTF;
	}

	public WebElement getHeaderIndustry() {
		return headerIndustry;
	}

	public WebElement getHeaderType() {
		return headerType;
	}

	
	public WebElement getPhoneNo() {
		return phoneNo;
	}

	public WebElement getActOrgName() {
		return actOrgName;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}
}
