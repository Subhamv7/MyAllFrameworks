package com.crm.objectRepositiryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//img[@alt=\"Create Product...\"]") private WebElement addProductBtn;
	public WebElement getAddProductBtn() {
		return addProductBtn;
	}

	@FindBy(name = "search_text") private WebElement searchTxtbox;
	public WebElement getSearchTxtbox() {
		return searchTxtbox;
	}
	@FindBy(name = "submit") private WebElement searchBtn;
	public WebElement getSearchBtn() {
		return searchBtn;

	}
}
