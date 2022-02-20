package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By header = By.cssSelector("div#logo a");
	private By accountSections = By.cssSelector("div#content h2");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	private By logOutLink = By.linkText("Logout");
	
	
	public String getAccountTitle() {
		return eleUtil.doGetTitle(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public boolean isLogOutLinkExist() {
		return eleUtil.doIsDisplayed(logOutLink);
	}

	public void logout() {
		if(isLogOutLinkExist()) {
			eleUtil.doClick(logOutLink);
		}
	}
	
	public List<String> getAccountSecList() {
		List<WebElement> acctSecList = eleUtil.waitForElementsToBeVisible(accountSections, 10);
		List<String> accSecText = new ArrayList<String>();
		for(WebElement e : acctSecList) {
			accSecText.add(e.getText());
		}
		return accSecText;
 	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("The product we are searching for is: "+productName);
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}
}
