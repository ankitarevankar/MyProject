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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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

public class CreateOrgWithIndusAndTypeTC2 extends BaseClass {
	@Test
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
		String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText(); // oip.getHeaderIndustry().getText();
		if (actIndustry.equals(industry)) {
			System.out.println(industry + "is created pass");
		} else {
			System.out.println(industry + "is not created fail");
		}
		String acttype = driver.findElement(By.id("dtlview_Type")).getText(); // oip.getHeaderType().getText();
		if (acttype.equals(type)) {
			System.out.println(type + "is created pass");
		} else {
			System.out.println(type + "is not created fail");
		}

	}

}
