package com.crm.objectRepositiryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webDriverUtility.WebdriverUtility;

public class CreateNewOrganizationPage {
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	 @FindBy(name="accountname")
	 private WebElement orgNameEdit;
	 @FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
	 private WebElement saveBtn;
	 @FindBy(name="industry")
	 private WebElement industryDD;
	
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	 public void createOrg(String orgName) {
		 orgNameEdit.sendKeys(orgName);
		 saveBtn.click();
	 }
	 WebdriverUtility wlib= new WebdriverUtility();
	 public void createOrg(String orgName, String industry) {
		 orgNameEdit.sendKeys(orgName);
		 wlib.selectValue(industryDD, industry);
		 saveBtn.click();
	 }
}
