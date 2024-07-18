package com.crm.objectRepositiryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webDriverUtility.WebdriverUtility;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	private @FindBy(linkText="Organizations")
	WebElement orgLink;
	private @FindBy(linkText="Contacts")
	WebElement contactLink;
	private @FindBy(linkText="Campaigns")
	WebElement campaignLink;
	private @FindBy(linkText="More")
	WebElement moreLink;
	@FindBy(xpath="//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement adminImg;
	private @FindBy(linkText="Sign Out")
	WebElement signoutLink;
	private @FindBy(linkText = "Products")
	WebElement productsLink;
	
	public WebElement getProductsLink() {
		return productsLink;
	}
	public WebElement getCampaignLink() {
		return campaignLink;
	}
	public WebElement getAdminImg() {
		return adminImg;
	}
	public WebElement getSignoutLink() {
		return signoutLink;
	}
	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public WebElement getOrgLink() {
		return orgLink;
	}
	public WebElement getContactLink() {
		return contactLink;
	}
	WebdriverUtility wlib = new WebdriverUtility();
	public void navigateToCompaignPage() {
		
		wlib.moveOnElement(driver, moreLink);
		campaignLink.click();
	}
	public void signout() {
		wlib.moveOnElement(driver, adminImg);
		signoutLink.click();
	}
	
}
