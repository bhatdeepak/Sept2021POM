package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 102: Test all account page functionality")
@Story("Story TNCUT123: Account Login, Page and Title tests")
public class AccountPageTest extends BaseTest{
	
	@BeforeClass 
	public void accPageSetup() {
		accPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
	}
	
	@Description("Test for the account page title")
	@Severity(SeverityLevel.MINOR)
	@Test (priority = 3)
	public void accPageTitleTest() {
		String accPageTitle = accPage.getAccountTitle();
		System.out.println("Account page title is: "+accPageTitle);
		Assert.assertEquals(accPageTitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test (priority = 2)
	public void accPageHeaderTest() {
		String actHeader = accPage.getAccountPageHeader();
		System.out.println("Account page header is "+actHeader);
		Assert.assertEquals(actHeader, Constants.ACCOUNT_PAGE_HEADER);
	}
	
	@Test (priority = 1)
	public void isLogOutExistTest() {
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	
	@Test (priority = 4)
	public void accPageSecListTest() {
		List<String> actSecList = accPage.getAccountSecList();
		Assert.assertEquals(actSecList, Constants.getAccPageSecList());
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
				{"MacBook"},
				{"Apple"},
				{"Samsung"},
		};
		
	}
	
	@Description("Product search on Account Page Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 5, dataProvider="productData")
	public void accSearchTest(String productName) {
		searchPage = accPage.doSearch(productName);
		Assert.assertTrue(searchPage.getProductsListCount()>0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
				{"MacBook", "MacBook Pro"},
				{"Apple", "Apple Cinema 30\""},
				{"Samsung", "Samsung SyncMaster 941BW"},
				{"Imac", "iMac"},
		};
		
	}
	
	
	
	@Test(priority=6, dataProvider = "productSelectData")
	public void selectProductTest(String product, String mainProduct) {
		searchPage = accPage.doSearch(product);
		prdInfoPage = searchPage.selectProduct(mainProduct);
		Map<String, String> prdInfoMap = prdInfoPage.getProductInfo(); 
		Assert.assertTrue(prdInfoMap.get("Product name").equalsIgnoreCase(mainProduct));
	}
	
}
