package com.crm.orgTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.FileUtility;
import com.crm.generic.webDriverUtility.JavaUtility;

public class CreateOrganizationTest {
	public static void main(String[] args) throws IOException {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		String browser = flib.getDataFromProperties("browser");
		//generate random number
		JavaUtility jlib =new JavaUtility();
		int ranInt = jlib.getRandomNumber();

		// read test script data from excel
		String orgname = elib.getDataFromExcel("org", 1, 2)+ranInt;
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
		String actorgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actorgname.equals(orgname)) {
			System.out.println(orgname +" is created");
		}else {
			System.out.println(orgname +" is not created");
		}
		driver.close();
	}

}
