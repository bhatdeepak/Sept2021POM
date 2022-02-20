package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	
	//1. Declared private driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. By locators
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By forgottenPassword = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By errorMsgEle = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	
	public String getLogingPageTitle() {
		//return driver.getTitle();
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean getLoginPageUrl() {
		//return driver.getCurrentUrl();
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isForgotPasswordLinkExist() {
		//return driver.findElement(forgottenPassword).isDisplayed();
		return eleUtil.doIsDisplayed(forgottenPassword);
	}
	
	public boolean isRegisterLinkExist() {
		//return driver.findElement(registerLink).isDisplayed();
		return eleUtil.doIsDisplayed(registerLink);
	}

	public AccountPage doLogin(String un, String pwd) {
		System.out.println("Logginig in with UserName: "+un+" and password: "+pwd);
//		driver.findElement(emailID).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		eleUtil.doSendKeys(emailID, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountPage(driver);
	}
	
	public boolean doLoginWithWrongCredentials (String un, String pwd) {
		System.out.println("Logginig in with UserName: "+un+" and password: "+pwd);
//		driver.findElement(emailID).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		eleUtil.doSendKeys(emailID, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errText = eleUtil.waitForElementToBeVisible(errorMsgEle, 10, 3000).getText();
		System.out.println("The login error is "+errText);
		if(errText.contains(Constants.LOGIN_ERROR_MSG)) {
			return false;
		}
		return true;
	}		
	
	public RegistrationPage goToRegistrationPage() {
		eleUtil.clickElementWhenReady(registerLink, 5);
		return new RegistrationPage(driver);
	}
}
	