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

public class CreateContactTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//create object of class
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		String browser = flib.getDataFromProperties("browser");
		//generate random number
		JavaUtility jlib =new JavaUtility();
		int ranInt = jlib.getRandomNumber();

		// read test script data from excel
		String contact =elib.getDataFromExcel("contact", 1, 2)+ranInt;
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.name("user_name")).sendKeys(flib.getDataFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(flib.getDataFromProperties("password"));
		driver.findElement(By.xpath("//input[@id=\"submitButton\"]")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		driver.findElement(By.name("lastname")).sendKeys(contact);
		driver.findElement(By.xpath("//input[@value=\"  Save  \"]")).click();
		String headertext= driver.findElement(By.className("dvHeaderText")).getText();
		if(headertext.contains(contact)) {
			System.out.println(contact+" is created");
		}else {
			System.out.println(contact+" is not created");
		}
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actLastName.equals(contact)) {
			System.out.println(contact+" is verified");
		}else {
			System.out.println(contact+" is not verifed");
		}
		driver.close();
	}

}
