package com.crm.orgTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.FileUtility;
import com.crm.generic.webDriverUtility.JavaUtility;
import com.crm.generic.webDriverUtility.WebdriverUtility;
import com.crm.objectRepositiryUtility.CreateNewOrganizationPage;
import com.crm.objectRepositiryUtility.HomePage;
import com.crm.objectRepositiryUtility.LoginPage;
import com.crm.objectRepositiryUtility.OrganizationInformationPage;
import com.crm.objectRepositiryUtility.OrganizationsPage;

public class CreateOrgTestUsingPOM {
	public static void main(String[] args) throws IOException {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		WebdriverUtility wlib = new WebdriverUtility();
		JavaUtility jlib= new JavaUtility();
		String browser = flib.getDataFromProperties("browser");
		WebDriver driver = null;
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else {
			System.out.println("Browser is not specified properly");
		}
		driver.manage().window().maximize();
		wlib.waitForPageToLoad(driver);
		driver.get(flib.getDataFromProperties("url"));
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(flib.getDataFromProperties("username"), flib.getDataFromProperties("password"));
		HomePage hp= new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
		String orgName = elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
		cnop.createOrg(orgName);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName +" is verified and pass");
		}
		else {
			System.out.println(orgName +" is not verified and Fail");
		}
		hp.signout();
		
		driver.quit();
	
	}
}
