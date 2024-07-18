package practiseTest;

import java.awt.Robot;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.generic.fileUtility.ExcelUtility;

public class GetProductInfoAmazontest {
	@Test(dataProvider = "getData")
	public void getProductInfo(String brandName, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
		
		//capture product info
		String x = "//span[text()='"+productName+"']/../../../..//div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		System.out.println(driver.findElement(By.xpath(x)).getText());
		driver.quit();
	}
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		ExcelUtility elib = new ExcelUtility();
		int rcount = elib.getRowCount("product");
		Object[][] objArr = new Object[rcount][2];
		for (int i = 0; i <rcount; i++) {
			objArr[i][0]=elib.getDataFromExcel("product",i+1, 0);
			objArr[i][1]=elib.getDataFromExcel("product",i+1, 1);
		}
		
		return objArr;
	}
}
