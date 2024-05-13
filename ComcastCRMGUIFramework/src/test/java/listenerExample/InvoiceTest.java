package listenerExample;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

//@Listeners(com.comcast.crm.listenerutility.ListenerImplementn.class)
public class InvoiceTest  {
	
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void actibateSim() {
		System.out.println("execute Test2");
		Assert.assertEquals("", "Login");
		System.out.println("s1");
		System.out.println("s2");
	}
//@Test
//	public void createInvoiceTest() {
//		System.out.println("execute Test1");
//		String acTitle=driver.getTitle();
//		Assert.assertEquals(acTitle, "Login");
//		System.out.println("s1");
//		System.out.println("s2");
//		
	//}
	
	
	
}
