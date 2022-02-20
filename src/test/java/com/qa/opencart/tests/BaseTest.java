package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	Properties properties;
	LoginPage loginPage;
	AccountPage accPage;
	SearchResultsPage searchPage;
	ProductInfoPage prdInfoPage;
	RegistrationPage regPage;
	SoftAssert softAssert;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		properties = df.init_prop();
		driver = df.init_driver();
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
