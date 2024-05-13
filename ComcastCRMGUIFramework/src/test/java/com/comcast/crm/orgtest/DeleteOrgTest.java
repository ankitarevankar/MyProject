package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest extends BaseClass {
	@Test
	public void deleteOrgTest() throws IOException, InterruptedException {
		// read data from excel

		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNum();

		// navigate to org module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		// click on createOrg btn
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all details & create new Org
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
		cnp.createOrg(orgname);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify header msg & expected result
		OrganizationInfoPage oinfo = new OrganizationInfoPage(driver);
		String headerinfo = oinfo.getHeaderMsg().getText();
		if (headerinfo.contains(orgname)) {
			System.out.println(orgname + "headermsg created pass");
		} else {
			System.out.println(orgname + "headermsg not created fail");
		}
//go back to org
		hp.getOrglink().click();

//search org
		op.getSearchTF().sendKeys(orgname);
		wlib.select(op.getSearchDD(), "Organization Name");
//In dynamic web Table select & delete org			
		op.getSearchNowBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']/../../td[8]/a[text()='del']")).click();
		Thread.sleep(2000); // ex for dyanamic xpath
		wlib.switchToAlertAccept(driver);

	}

}
