package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By fName = By.id("input-firstname");
	private By sName = By.id("input-lastname");
	private By emailID = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By pwd = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]//input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]//input[@type='radio']");
	private By successMsg = By.cssSelector("div#content h1");
	
	
	private By agreeCheckBox = By.name("agree");
	private By btnContinue = By.xpath("//input[@type='submit' and @value='Continue']");
	private By registerLink = By.linkText("Register");
	private By logoutLink = By.linkText("Logout");	
	
	public boolean accountRegistration(String fName, String sName, String emailID, 
			String telephone, String pwd, String confirmPwd, String subscribe) {
		
		eleUtil.doSendKeys(this.fName, fName);
		eleUtil.doSendKeys(this.sName, sName);
		eleUtil.doSendKeys(this.emailID, emailID);
		eleUtil.doSendKeys(this.telePhone, telephone);
		eleUtil.doSendKeys(this.pwd, pwd);
		eleUtil.doSendKeys(this.confirmPwd, confirmPwd);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(btnContinue);
		
		String messg = eleUtil.waitForElementToBeVisible(successMsg, 10, 3000).getText();
		
		if(messg.contains(Constants.ACCOUNT_CREATION_SUCCESS)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
