package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("Epic 101: Design Open Cart App - Login Page")
@Story("Us 101: Open Cart Login Design with Multiple features")
public class LoginPageTest extends BaseTest {

	@Description("login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test (priority=1)
	public void LoginPageTitleTest() {
		String actTitle = loginPage.getLogingPageTitle();
		System.out.println("Page Title: "+actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test (priority=2)
	public void LoginPageUrlTest() {
		//String actUrl = loginPage.getLoginPageUrl();
		//Assert.assertEquals(actUrl, Constants.LOGIN_PAGE_URL);
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Description("Does forgot passwore link exist")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}

	@Test (priority=4)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Test (priority=5)
	public void loginTest() {
		accPage = loginPage.doLogin(properties.getProperty("username").trim(),properties.getProperty("password").trim());
		Assert.assertEquals(accPage.getAccountTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}
}
