package com.crm.BaseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.crm.generic.databaseUtility.DataBaseUtility;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.FileUtility;
import com.crm.generic.webDriverUtility.JavaUtility;
import com.crm.generic.webDriverUtility.UtilityClassObject;
import com.crm.generic.webDriverUtility.WebdriverUtility;
import com.crm.objectRepositiryUtility.HomePage;
import com.crm.objectRepositiryUtility.LoginPage;

public class BaseClass {
	public DataBaseUtility dlib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public WebdriverUtility wlib = new WebdriverUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib =new JavaUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver=null;
	public ExtentReports report;
	
	@BeforeSuite(groups = {"SmokeTest","RegressionTest"})
	public void configBeforeSuite() {
		System.out.println("==Connect to Db, Report config");
		dlib.getDbConnection();
		
		}
	
	@BeforeClass(groups = {"SmokeTest","RegressionTest"})
	public void configBeforeClass() throws IOException {
		System.out.println("Launch browser");
		
		String browser = flib.getDataFromProperties("browser");
		
		if(browser.equals("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}else
		{
			driver = new ChromeDriver();
		}
		sdriver= driver;
		UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod(groups = {"SmokeTest","RegressionTest"})
	public void configBM() throws IOException {
		System.out.println("Login");
		driver.manage().window().maximize();
		wlib.waitForPageToLoad(driver);
		driver.get(flib.getDataFromProperties("url"));
	    LoginPage lp = new LoginPage(driver);
	    lp.loginToApp(flib.getDataFromProperties("username"), flib.getDataFromProperties("password"));
		
	}
	@AfterMethod(groups = {"SmokeTest","RegressionTest"})
	public void configAM() {
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		hp.signout();
		
	}
	@AfterClass(groups = {"SmokeTest","RegressionTest"})
	public void configAfterClass() {
		System.out.println("Close browser");
		driver.quit();
	}
	@AfterSuite
	public void configAfterSuite() {
		System.out.println("DisConnect to DB, Report config ");
		dlib.closeDbConnection(); 
		
	}

}
