package dataproviderExample;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class DataProviderFromExcel {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productname) {
	WebDriver driver= new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	driver.get("https://flipkart.com/");
	
	driver.findElement(By.name("q")).sendKeys(brandName,Keys.ENTER);
	//capture product info
	String s="//div[text()='"+productname+"']/../../div[2]/div[1]/div/div";
	String price=driver.findElement(By.xpath(s)).getText();
	
	System.out.println(price);
	}
@DataProvider	
public Object[][] getData() throws EncryptedDocumentException, IOException{
	ExcelUtility elib=new ExcelUtility();
       int rowCount =elib.getRowCount("product");
	Object[][] obj= new Object[rowCount][2];
	for(int i=0;i<rowCount;i++)
	{
		obj[i][0]=elib.getDataFromExcel("product", i+1, 0);
		obj[i][1]=elib.getDataFromExcel("product", i+1, 1);
		
			
	}
	
	return obj;
	
}

}
