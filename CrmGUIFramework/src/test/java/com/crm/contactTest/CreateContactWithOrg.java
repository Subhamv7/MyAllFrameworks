package com.crm.contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.FileUtility;
import com.crm.generic.webDriverUtility.JavaUtility;
import com.crm.generic.webDriverUtility.WebdriverUtility;

public class CreateContactWithOrg {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		WebdriverUtility wlib = new WebdriverUtility();
		String browser = flib.getDataFromProperties("browser");
		//generate random number
		JavaUtility jlib =new JavaUtility();
		int ranInt = jlib.getRandomNumber();

		// read test script data from excel
		String orgname = elib.getDataFromExcel("contact",7,2)+ranInt;
		String contactLastName = elib.getDataFromExcel("contact", 7, 3)+ranInt;
		WebDriver driver = null;
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
		driver.get(flib.getDataFromProperties("url"));
		driver.manage().window().maximize();
		wlib.waitForPageToLoad(driver);
		driver.findElement(By.name("user_name")).sendKeys(flib.getDataFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(flib.getDataFromProperties("password"));
		driver.findElement(By.xpath("//input[@id=\"submitButton\"]")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("//input[@class=\"crmbutton small save\"]")).click();

		// verify the header msg expected result and org info
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgname)) {
			System.out.println(orgname +" is created");
		}else {
			System.out.println(orgname +" is not created");
		}
		// creation of the contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name=\"account_name\"]/following-sibling::img")).click();

		wlib.switchToTabOnURL(driver, "module=Accounts");
	

		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[@class=\"lvtCol\"]/../../tr[2]/td/a")).click();

		// switch to parent window
		wlib.switchToTabOnURL(driver,"module=Contacts&action/");

		driver.findElement(By.xpath("//input[@value=\"  Save  \"]")).click();
		String headertext= driver.findElement(By.className("dvHeaderText")).getText();
		if(headertext.contains(contactLastName)) {
			System.out.println(contactLastName+" is created");
		}else {
			System.out.println(contactLastName+" is not created");
		}
		driver.quit();


	}

}
