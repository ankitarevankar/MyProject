package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;



/**
 * test class for contact module
 * @author Admin
 */
public class SearchContactTest extends BaseClass{
/**
 * Scenario:login()=>navigate contact=>createContact=>verify
 */
	@Test
	public void searchContact() 
	{
		/*s1: login to app   */
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
