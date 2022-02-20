package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	WebDriver driver;
	ElementUtil eleUtil;
	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By prodImages = By.cssSelector("ul.thumbnails img");
	private By prodMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By prodPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	
	private Map<String, String> mapProdInfo;
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public Map<String,String> getProductInfo() {
		
		mapProdInfo = new LinkedHashMap<String, String>();
		mapProdInfo.put("Product name", eleUtil.getElement(productHeader).getText());
		getProdMetaData();
		getProdPriceData();
		return mapProdInfo;
	}
	
	private void getProdMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(prodMetaData);
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
		for(WebElement e: metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			mapProdInfo.put(metaKey, metaValue);
		}
	}
	
	private void getProdPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(prodPriceData);
		
		String price = metaPriceList.get(0).getText().trim();
		String exPrice = metaPriceList.get(1).getText().trim();
		mapProdInfo.put("price", price);
		mapProdInfo.put("ExTaxPrice", exPrice);
	}
		
}
