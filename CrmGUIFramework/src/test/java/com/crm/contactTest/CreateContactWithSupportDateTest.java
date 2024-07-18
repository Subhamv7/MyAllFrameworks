package com.crm.contactTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.FileUtility;
import com.crm.generic.webDriverUtility.JavaUtility;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		String browser = flib.getDataFromProperties("browser");
		//generate random number
		JavaUtility jlib =new JavaUtility();
		int ranInt = jlib.getRandomNumber();
		// read test script data from excel

		String contact = elib.getDataFromExcel("contact", 4, 2)+ranInt;
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
		String startDate= jlib.getSystemDateYYYYMMDD();

		String endDate =jlib.getRequiredDateYYYYMMDD(30);

		driver.get(flib.getDataFromProperties("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.name("user_name")).sendKeys(flib.getDataFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(flib.getDataFromProperties("password"));
		driver.findElement(By.xpath("//input[@id=\"submitButton\"]")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		driver.findElement(By.name("lastname")).sendKeys(contact);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		driver.findElement(By.xpath("//input[@value=\"  Save  \"]")).click();
		String actualStartDate= driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actualStartDate.equals(startDate)) {
			System.out.println(startDate+" is verified");
		}else {
			System.out.println(startDate+" is not verified");
		}
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.equals(endDate)) {
			System.out.println(endDate+" is verified");
		}else {
			System.out.println(endDate+" is not verifed");
		}
		driver.close();


	}

}
