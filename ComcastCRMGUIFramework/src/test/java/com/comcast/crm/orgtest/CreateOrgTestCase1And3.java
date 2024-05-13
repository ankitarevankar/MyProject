package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObj;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerutility.ListenerImplementn;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.comcast.crm.listenerutility.ListenerImplementn.class)
public class CreateOrgTestCase1And3 extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrgTestCase1And3() throws IOException {

		// read data from excel
		UtilityClassObj.getTest().log(Status.INFO, "read data from xml");
		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNum();
		String phno = elib.getDataFromExcel("org", 1, 5);
		// navigate to org module
		UtilityClassObj.getTest().log(Status.INFO, "navigate to org module");
        HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		// click on createOrg btn
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all details & create new Org
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
		cnp.createOrg(orgname);
		UtilityClassObj.getTest().log(Status.INFO, orgname + "create new org");

		// driver.findElement(By.name("phone")).sendKeys(phno);
		cnp.createphnum(phno);

    //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		cnp.getSaveBtn().click();
		// verify header orgmsg & orgname
		OrganizationInfoPage oinfo = new OrganizationInfoPage(driver);
		String headerinfo = oinfo.getHeaderMsg().getText();
		boolean status = headerinfo.contains(orgname);
		Assert.assertEquals(status, true);
//	    String headerinfo	=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(headerinfo.contains(orgname)) {
//			System.out.println(orgname+ "headermsg created pass");
//		}else {
//			System.out.println(orgname+ "headermsg not created fail");
//			
//		}
//			//verify orgname & exp orgname 
		String actOrgname = oinfo.getActOrgName().getText();
		Assert.assertEquals(actOrgname, orgname);

//		String actOrgname	=driver.findElement(By.id("dtlview_Organization Name")).getText();
//		if(actOrgname.equals(orgname)) {
//			System.out.println(orgname+" created pass");
//		}else {
//			System.out.println(orgname+"is not created fail");
//		}

		// vrify phno tc3
		String actphn = oinfo.getPhoneNo().getText();
		Assert.assertEquals(actphn, phno);
//		String phnumber	=driver.findElement(By.id("dtlview_Phone")).getText();
//		if(phnumber.equals(phno)) {
//			System.out.println(phno+"phnum created pass");
//		}else {
//			System.out.println(phno+"phnum not created fail");
//		}
//		
	}

	@Test(groups = "regressionTest")
	public void createOrgWithIndusAndTypeTC2() throws IOException {

		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNum();
		String industry = elib.getDataFromExcel("org", 1, 3);
		String type = elib.getDataFromExcel("org", 1, 4);
		// login to app
		// navigate to org module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		// click on createOrg btn
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click(); // driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
		cnp.createIndustry(orgname, industry, type); // driver.findElement(By.name("accountname")).sendKeys(orgname);
		// WebElement industryOp=driver.findElement(By.name("industry"));
		// WebElement typeOp=driver.findElement(By.name("accounttype"));

//verify industry & type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actIndustry = oip.getHeaderIndustry().getText();
		Assert.assertEquals(actIndustry, industry);
//		String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
//		if (actIndustry.equals(industry)) {
//			System.out.println(industry + "is created pass");
//		} else {
//			System.out.println(industry + "is not created fail");
//		}
		String actType = oip.getHeaderType().getText();
		Assert.assertEquals(actType, type);
//		String acttype = driver.findElement(By.id("dtlview_Type")).getText();
//		if (acttype.equals(type)) {
//			System.out.println(type + "is created pass");
//		} else {
//			System.out.println(type + "is not created fail");
//		}

	}

	@Test(groups = "regressionTest")
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
		boolean status = headerinfo.contains(orgname);
		Assert.assertEquals(status, true);
//		String headerinfo = oinfo.getHeaderMsg().getText();
//		if (headerinfo.contains(orgname)) {
//			System.out.println(orgname + "headermsg created pass");
//		} else {
//			System.out.println(orgname + "headermsg not created fail");
//		}
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
