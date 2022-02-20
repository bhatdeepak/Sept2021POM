package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By productResults = By.cssSelector("div.caption a");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getProductsListCount() {
		int productCount = eleUtil.waitForElementsToBeVisible(productResults, 10, 2000).size();
		System.out.println("The product search count "+productCount);
		return productCount;
	}
	
	public ProductInfoPage selectProduct(String mainProduct) {
		List<WebElement> productsList = eleUtil.waitForElementsToBeVisible(productResults, 10, 2000);
		for(WebElement e: productsList) {
			String text = e.getText();
			if(text.equalsIgnoreCase(mainProduct)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	
}
