package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class SauceTest {

	public void getHighestValueAndAddToCart() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.saucedemo.com/");
		By userName = By.id("user-name");
		By pwd = By.id("password");
		By loginBtn = By.id("login-button");
		By priceListEle = By.className("inventory_item_price");
		
		
		driver.findElement(userName).sendKeys("standard_user");
		driver.findElement(pwd).sendKeys("secret_sauce");
		driver.findElement(loginBtn).click();
		
		List<WebElement> priceList = driver.findElements(priceListEle);
		
		double max_price = priceList.stream().mapToDouble(e -> Double.parseDouble(e.getText().trim().replace("$",""))).max().getAsDouble();
		
		System.out.println(max_price);
	    By buttonPath = By.xpath("//div[normalize-space()='$49.99']/following-sibling::button[text()='Add to cart']");
	    
	    
	
		
	}
	
}
