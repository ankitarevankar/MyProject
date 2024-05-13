package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactWithOrg extends BaseClass {
	@Test
	public void createContactWithOrg() throws IOException, InterruptedException {
		// read data from excel
		String orgname = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNum();
		String contactlastname = elib.getDataFromExcel("contact", 1, 4) + jlib.getRandomNum();

		// navigate to org module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		// click on createOrg btn
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all details & create new Org

		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrg(orgname);
		cop.getSaveBtn().click();
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgname)) {
			System.out.println(orgname + "contains same passs");
		} else {
			System.out.println(orgname + "not contains same fail");
		}
		// navigate to contact module
		hp.getContactlink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		CreatingNewContactPage cnp1 = new CreatingNewContactPage(driver);
		cnp1.createContactLastName(contactlastname);
		cnp1.getInsideplusBtn().click(); // driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		// switch to child window
		wlib.switchToTabOnUrl(driver, "module=Accounts");

		Thread.sleep(2000);
		cnp1.sendOrgnameInsideSearchTF(orgname);
		cnp1.getInsideSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
		Thread.sleep(1000);
		// switch to parent window
		wlib.switchToTabOnUrl(driver, "module=Contacts&action");
		cnp1.getSaveBtn().click();

		// verify header contactlastname info exp res
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo.contains(contactlastname)) {
			System.out.println(contactlastname + "header is verified pass");
		} else {
			System.out.println(contactlastname + "header is not verified fail");

		}
		// verify header orgname info exp res
		String actOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgname.trim().equals(orgname)) {
			System.out.println(orgname + "is created pass");
		} else {
			System.out.println(orgname + "is not created fail");
		}

	}

}
