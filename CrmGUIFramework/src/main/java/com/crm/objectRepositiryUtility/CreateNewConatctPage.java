package com.crm.objectRepositiryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewConatctPage {
	WebDriver driver;
	public CreateNewConatctPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="lastname")
	private WebElement lastNameEdit;
	@FindBy(xpath="//input[@title ='Save [Alt+S]']")
	private WebElement saveBtn;
	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}

}
