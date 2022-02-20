package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registrationSetup() {
		regPage = loginPage.goToRegistrationPage();
	}
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String emailID = "DeepAuto"+randomGenerator.nextInt(1000)+"@gmail.com";
		return emailID;
	}
	
	@DataProvider
	public Object[][] getRegistrationData(){
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegistrationData")
	public void userRegTest(String fName, String sName, String emailID, String telephone, String pwd, String confirmPwd, String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(fName, sName, getRandomEmail(),
				telephone, pwd, confirmPwd, subscribe));
		
	}
	
}
