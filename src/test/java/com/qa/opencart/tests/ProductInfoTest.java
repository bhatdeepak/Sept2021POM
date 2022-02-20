package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass 
	public void prodInfoSetup() {
		accPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
	}
	
	@Test (priority=1)
	public void productHeaderTest(){
		searchPage = accPage.doSearch("MacBook Pro");
		prdInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> prdInfoMap = prdInfoPage.getProductInfo();
		Assert.assertEquals(prdInfoMap.get("Product name"), "MacBook Pro");
	}
	
	@Test(priority=2)
	public void productInfoTest() {
		searchPage = accPage.doSearch("MacBook Pro");
		prdInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> prdInfoMap = prdInfoPage.getProductInfo();
		prdInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		softAssert.assertEquals(prdInfoMap.get("Brand"), "Apple1");
		softAssert.assertEquals(prdInfoMap.get("Reward Points"), "8001");
		softAssert.assertEquals(prdInfoMap.get("Product Code"), "Product 18");
		softAssert.assertAll();
	}

}
