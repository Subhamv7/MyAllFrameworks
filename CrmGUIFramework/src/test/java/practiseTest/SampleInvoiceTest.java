package practiseTest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.BaseTest.BaseClass;

import junit.framework.Assert;
@Listeners(com.crm.ListenersUtility.ListenerImplementation.class)
public class SampleInvoiceTest extends BaseClass {
	@Test
	public void CreateInvoiceTest() {
		System.out.println("Execute CreateInvoiceTest");
		String acttitle = driver.getTitle();
		Assert.assertEquals(acttitle,"Login");
		//[Hard assert, this assertEquals() generate a exception called AssertError]
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

	@Test
	public void CreateInvoicewithContactTest() {
		System.out.println("Execute CreateInvoicewithContactTest");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	@Test(retryAnalyzer = com.crm.ListenersUtility.RetryListenerImp.class,enabled = false)
	public void ActivateSim() {
		System.out.println("ActivateSim start");
		System.out.println("Step-1");
		String acttitle = driver.getTitle();
		Assert.assertEquals(acttitle,"Login");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}
