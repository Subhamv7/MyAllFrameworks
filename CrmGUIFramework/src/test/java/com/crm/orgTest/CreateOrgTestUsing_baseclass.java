package com.crm.orgTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.crm.BaseTest.BaseClass;
import com.crm.objectRepositiryUtility.CreateNewOrganizationPage;
import com.crm.objectRepositiryUtility.HomePage;
import com.crm.objectRepositiryUtility.OrganizationInformationPage;
import com.crm.objectRepositiryUtility.OrganizationsPage;

public class CreateOrgTestUsing_baseclass extends BaseClass {
	@Test
	public void CreateOrGBaseClass() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		cnop.createOrg(orgName);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " is verified and pass");
		} else {
			System.out.println(orgName + " is not verified and Fail");
		}
	}
}
