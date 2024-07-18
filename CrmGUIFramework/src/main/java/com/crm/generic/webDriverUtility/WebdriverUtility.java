package com.crm.generic.webDriverUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtility {
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void switchToTabOnURL(WebDriver driver, String partialURL) {
		Set<String> allwh1 = driver.getWindowHandles();
		Iterator<String> it1 = allwh1.iterator();
		while(it1.hasNext()) {
			String windowId = it1.next();
			driver.switchTo().window(windowId);
			String acturl = driver.getCurrentUrl();
			if(acturl.contains(partialURL)) {
				break;
			}
		}
	}
	public void switchToTabOnTitle(WebDriver driver, String partialTitle) {
		Set<String> allwh1 = driver.getWindowHandles();
		Iterator<String> it1 = allwh1.iterator();
		while(it1.hasNext()) {
			String windowId = it1.next();
			driver.switchTo().window(windowId);
			String actTitle = driver.getTitle();
			if(actTitle.contains(partialTitle)) {
				break;
			}
		}
	}
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver, String nameId) {
		driver.switchTo().frame(nameId);
	}
	public void switchToFrame(WebDriver driver, WebElement ele) {
		driver.switchTo().frame(ele);
	}
	public void switchToAlertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void switchToAlertDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void selectVisibleText(WebElement ele, String text) {
		Select s = new Select(ele);
		s.selectByVisibleText(text);
	}
	public void selectIndex(WebElement ele, int index) {
		Select s = new Select(ele);
		s.selectByIndex(index);
	}
	public void selectValue(WebElement ele, String value) {
		Select s = new Select(ele);
		s.selectByValue(value);
	}
	public void moveOnElement(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}
	public void doubleClick(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.doubleClick(ele).perform();
	}
	public void dragAndDrop(WebDriver driver, WebElement ele1, WebElement ele2) {
		Actions act = new Actions(driver);
		act.dragAndDrop(ele1, ele2);
	}
	public Select handleDropdown(WebElement ele) {
		Select s = new Select(ele);
		return s;
	}
	public void value(WebElement ele, String value) {
		handleDropdown(ele).selectByValue(value);
	}
	
}
