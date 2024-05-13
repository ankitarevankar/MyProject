package com.comcast.crm.generic.webdriverutility;


import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

public void waitForPageToLoad(WebDriver driver)
{
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));	
}
public void waitForElemenPresent(WebDriver driver,WebElement element) 
{
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(element));
}
public void switchToTabOnUrl(WebDriver driver, String partialUrl)
{
Set<String> set	=driver.getWindowHandles();
Iterator<String>it =set.iterator();
while(it.hasNext()) {
String winId = it.next();
driver.switchTo().window(winId);
String actUrl =driver.getCurrentUrl();
if(actUrl.contains(partialUrl)){
	break;
    }
  }
}
public void switchToTabTitle(WebDriver driver,String partialTitle) {
	Set<String>set=driver.getWindowHandles();
	Iterator<String>it =set.iterator();
	while(it.hasNext()) {
	String windId	=it.next();
	driver.switchTo().window(windId);
String actTitle	=driver.getTitle();
if(actTitle.contains(partialTitle)) {
	break;
     }

  }
}
public void switchToFrame(WebDriver driver,int index) {
	driver.switchTo().frame(index);
	}
public void switchToFrame(WebDriver driver,String name) {
	driver.switchTo().frame(name);	
}
public void switchToFrame(WebDriver driver,WebElement element) {
	driver.switchTo().frame(element);	
}
public void switchToAlertAccept(WebDriver driver) {
	driver.switchTo().alert().accept();
}
public void switchToAlertDismiss(WebDriver driver) {
	driver.switchTo().alert().dismiss();
}

public void select(WebElement element,int index) {
Select sele= new Select(element);
sele.selectByIndex(index);	
}
public void select(WebElement element,String text) {
Select sele= new Select(element);
sele.selectByVisibleText(text);
}

public void mouseMoveOnElement(WebDriver driver,WebElement element) {
Actions actions= new Actions(driver);
actions.moveToElement(element).perform();
	}
public void doubleclick(WebDriver driver,WebElement element) {
Actions actions= new Actions(driver);
actions.doubleClick(element).perform();
	}

}
