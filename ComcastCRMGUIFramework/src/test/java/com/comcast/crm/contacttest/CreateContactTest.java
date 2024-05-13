package com.comcast.crm.contacttest;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

/**
 * @author Admin
 */
public class CreateContactTest extends BaseClass {
	@Test // (groups="smokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException {
		/* read testscript data from excel */
		String lastName = elib.getDataFromExcel("contact", 1, 3) + jlib.getRandomNum();
		wlib.waitForPageToLoad(driver);
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();
		// click on createcontact btn
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click(); // driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// enter all details & create new Org
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContactLastName(lastName); // driver.findElement(By.name("lastname")).sendKeys(lastName);
		ccp.getSaveBtn().click(); // driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		// verify lastname contact header
//		String actHeader = driver.findElement(By.className("dvHeaderText")).getText();	
//		if (actHeader.contains(lastName)) {
//			System.out.println(lastName + "contains same passs");
//		} else {
//			System.out.println(lastName + "not contains same fail");
//		}
		// used hardeAssert
		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);
		// verify lastname
//		String actlastName = driver.findElement(By.id("dtlview_Last Name")).getText(); // cip.getHeaderContact().getText();
//		if (actlastName.equals(lastName)) {
//			System.out.println(lastName + "is created pass");
//		} else {
//			System.out.println(lastName + "is not created fail");
//		}
		// used soft Assert
		String actlastName = cp.getLastName().getText();
		SoftAssert asob = new SoftAssert();
		asob.assertEquals(actlastName, lastName);
	}

	@Test(groups = "regressionTest")
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
//		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if (headerInfo.contains(orgname)) {
//			System.out.println(orgname + "contains same passs");
//		} else {
//			System.out.println(orgname + "not contains same fail");
//		}
		String headerInfo = cop.getOrgHeader().getText();
		boolean status = headerInfo.contains(orgname);
		Assert.assertEquals(status, true);
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
		String headerinfo = cp.getHeaderMsg().getText();
		boolean status1 = headerinfo.contains(contactlastname);
		Assert.assertEquals(status1, true);
//		if (headerinfo.contains(contactlastname)) {
//			System.out.println(contactlastname + "header is verified pass");
//		} else {
//			System.out.println(contactlastname + "header is not verified fail");
//        }
		// verify header orgname info exp res
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actOrgname =oip.getOrgTF().getText();
		Assert.assertEquals(actOrgname.trim(), orgname);
//		String actOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
//		if (actOrgname.trim().equals(orgname)) {
//			System.out.println(orgname + "is created pass");
//		} else {
//			System.out.println(orgname + "is not created fail");
//		}

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws IOException {
		// read data from excel
		String lastname = elib.getDataFromExcel("contact", 2, 3) + jlib.getRandomNum();

		// navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();
		// click on createOrg btn
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click(); // driver.findElement(By.xpath("//img[@title='Create
												// Contact...']")).click();

		// enter all details & create new contact start date
		String startDate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYYYDDMM(30);
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);

		cnp.createContactLastName(lastname); // driver.findElement(By.name("lastname")).sendKeys(lastname);
		cnp.getSupportStartDate().clear(); // driver.findElement(By.name("support_start_date")).clear();
		cnp.getSupportStartDate().sendKeys(startDate);
		// support end date

		cnp.getSupportEndDate().clear(); // driver.findElement(By.name("support_end_date")).clear();
		cnp.getSupportEndDate().sendKeys(endDate); // driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		cnp.getSaveBtn().click();

		// verify support start date & end date
		ContactInfoPage cip = new ContactInfoPage(driver);

		String actstartDate = cip.getSupportStartDateView().getText();// driver.findElement(By.id("dtlview_Support Start
																		// Date")).getText();
		Assert.assertEquals(actstartDate, startDate);

//		if (actstartDate.equals(startDate)) {
//			System.out.println(startDate + "is verified pass");
//		} else {
//			System.out.println(startDate + "is not verified fail");
//         }
		String actendDate = cip.getSupportEndDateView().getText();// driver.findElement(By.id("dtlview_Support End
																	// Date")).getText();
		Assert.assertEquals(actendDate, endDate);
//		if (actendDate.equals(endDate)) {
//			System.out.println(endDate + "is verified pass");
//		} else {
//			System.out.println(endDate + "is not verified fail");
//             }

	}

}
