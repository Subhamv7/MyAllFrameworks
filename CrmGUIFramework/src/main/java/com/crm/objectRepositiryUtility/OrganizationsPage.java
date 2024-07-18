package com.crm.objectRepositiryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@alt=\"Create Organization...\"]")
	private	WebElement createNewOrgBtn;

	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	@FindBy(name="search_text")
	private	WebElement searchEdit;
	@FindBy(xpath="//select[@id=\"bas_searchfield\"]")
	private	WebElement searchDD	;
	
	
	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSearchEdit() {
		return searchEdit;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	@FindBy(xpath="//input[@name=\"submit\"]")
	private	WebElement searchBtn;

}
