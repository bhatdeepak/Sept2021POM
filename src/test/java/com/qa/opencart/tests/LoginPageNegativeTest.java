package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] negativeLoginData(){
		return new Object[][] {
			{"test323","tt35"},
			{"testddaa","derer"},
			{"","tt35e444"},
			{"",""},
			{"testccdw323","tt35ddddrre33"},
		};
	}
	
	@Test (dataProvider = "negativeLoginData")
	public void doNegativeLoginTest(String userName, String password) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(userName, password));
	}

}
