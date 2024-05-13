package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDateTest extends BaseClass {
@Test
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
		String actstartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actstartDate.equals(startDate)) {
			System.out.println(startDate + "is verified pass");
		} else {
			System.out.println(startDate + "is not verified fail");

		}
		String actendDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (actendDate.equals(endDate)) {
			System.out.println(endDate + "is verified pass");
		} else {
			System.out.println(endDate + "is not verified fail");

		}

	}

}
