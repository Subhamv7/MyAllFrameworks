package com.crm.objectRepositiryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	 WebDriver driver;
	 public ContactInformationPage(WebDriver driver){
		 this.driver = driver;
		 PageFactory.initElements(driver,this);
	 }
	@FindBy(className="dvHeaderText")
 private WebElement headerTxt;
	public WebElement getHeaderTxt() {
		return headerTxt;
	}

}
