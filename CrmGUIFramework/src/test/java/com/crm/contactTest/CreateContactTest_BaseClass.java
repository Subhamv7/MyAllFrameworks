package com.crm.contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.crm.BaseTest.BaseClass;
import com.crm.generic.webDriverUtility.UtilityClassObject;
import com.crm.objectRepositiryUtility.ContactInformationPage;
import com.crm.objectRepositiryUtility.ContactPage;
import com.crm.objectRepositiryUtility.CreateNewConatctPage;
import com.crm.objectRepositiryUtility.CreateNewOrganizationPage;
import com.crm.objectRepositiryUtility.HomePage;
import com.crm.objectRepositiryUtility.OrganizationInformationPage;
import com.crm.objectRepositiryUtility.OrganizationsPage;

import junit.framework.Assert;
@Listeners(com.crm.ListenersUtility.ListenerImplementation.class)
public class CreateContactTest_BaseClass extends BaseClass {
	@Test(groups = {"RegressionTest","SmokeTest"})
	public void CreateOrGBaseClass() throws EncryptedDocumentException, IOException {
		
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org Page");
		op.getCreateNewOrgBtn().click();
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		
		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		
		UtilityClassObject.getTest().log(Status.INFO, "Create new org");
		cnop.createOrg(orgName);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		
		UtilityClassObject.getTest().log(Status.INFO, "org created");
		Assert.assertEquals(true, actOrgName.contains(orgName));
//		if (actOrgName.contains(orgName)) {
//			System.out.println(orgName + " is verified and pass");
//		} else {
//			System.out.println(orgName + " is not verified and Fail");
//		}
	}

	@Test(groups = "SmokeTest",invocationCount = -1)
	public void CreateContact() throws EncryptedDocumentException, IOException {
		int ranInt = jlib.getRandomNumber();

		// read test script data from excel
		String contact = elib.getDataFromExcel("contact", 1, 2) + ranInt;
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewConBtn().click();
		CreateNewConatctPage cnp = new CreateNewConatctPage(driver);
		cnp.getLastNameEdit().sendKeys(contact);
		cnp.getSaveBtn().click();
		ContactInformationPage cip= new ContactInformationPage(driver);
		String headertext = cip.getHeaderTxt().getText();
		if (headertext.contains(contact)) {
			System.out.println(contact + " is created");
		} else {
			System.out.println(contact + " is not created");
		}
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(contact)) {
			System.out.println(contact + " is verified");
		} else {
			System.out.println(contact + " is not verifed");
		}

	}
	

}
