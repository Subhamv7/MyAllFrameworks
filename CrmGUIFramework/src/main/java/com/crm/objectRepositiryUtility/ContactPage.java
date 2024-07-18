package com.crm.objectRepositiryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	@FindBy(xpath="//img[@alt=\"Create Contact...\"]")
	private WebElement createNewConBtn;

	public WebElement getCreateNewConBtn() {
		return createNewConBtn;
	}
	
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

}
