package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver){ //rule 3 object initialization	
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactlink;

	@FindBy(linkText="Campaigns")
	private WebElement campaignlink;

	@FindBy(linkText="More")
	private WebElement morelink;

	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImage;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	

	public WebElement getOrglink() {
		return orglink;
	}


	public WebElement getContactlink() {
		return contactlink;
	}


	public WebElement getCampaignlink() {
		return campaignlink;
	}


	public WebElement getMorelink() { //get method access single action
		return morelink;
	}
public void navigateToCampaginPage() {   //ex for business libraries
	Actions actions = new Actions(driver);
	actions.moveToElement(morelink).perform();
	campaignlink.click();
}
public void logout() {
	Actions actions = new Actions(driver);
	actions.moveToElement(adminImage).perform();
	signOutLink.click();
}
}
