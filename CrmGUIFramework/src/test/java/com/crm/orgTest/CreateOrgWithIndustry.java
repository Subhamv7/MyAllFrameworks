package com.crm.orgTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.FileUtility;
import com.crm.generic.webDriverUtility.JavaUtility;

public class CreateOrgWithIndustry {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		String browser = flib.getDataFromProperties("browser");
		//generate random number
		JavaUtility jlib =new JavaUtility();
		int ranInt = jlib.getRandomNumber();

		// read test script data from excel
		String orgname = elib.getDataFromExcel("org", 4,2)+ranInt;
		String industry = elib.getDataFromExcel("org", 4,3);
		String type = elib.getDataFromExcel("org", 4,4);
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
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();

		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		Select s = new Select(driver.findElement(By.name("industry")));
		s.selectByValue("Energy");
		Select s1 = new Select(driver.findElement(By.name("accounttype")));
		s1.selectByValue("Press");
		driver.findElement(By.xpath("//input[@class=\"crmbutton small save\"]")).click();
		
		// verify the dropdown industry and type info
		
		String actindustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actindustry.equals(industry)) {
			System.out.println(industry +" is verified");
		}else {
			System.out.println(industry +" is not verified");
		}
		String acttype = driver.findElement(By.id("dtlview_Type")).getText();
		if(acttype.equals(type)) {
			System.out.println(type +" is verified");
		}else {
			System.out.println(type +" is not verified");
		}
		driver.close();
	}
}
