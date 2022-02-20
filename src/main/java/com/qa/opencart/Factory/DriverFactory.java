package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties properties;
	public static String highlight;
	private OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to initialize the webdriver
	 * @param browserName
	 * @return this will return the webdriver
	 */

	public WebDriver init_driver() {
		
		String browserName = properties.getProperty("browser").trim();
		String url = properties.getProperty("url").trim();
		
		System.out.println("browser name is : "+browserName);
		
		highlight = properties.getProperty("highlight");
		
		optionsManager = new OptionsManager(properties);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}else if (browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
		}else if (browserName.equalsIgnoreCase("Safari")) {
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(url);
		return getDriver();
	}
	
	public static synchronized 	WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialize the properties
	 * @return 
	 * This method will return the properties object reference.
	 * 
	 */
	
	public Properties init_prop() {
		properties = new Properties();
		
		FileInputStream ip = null;
		String testEnv = System.getProperty("env");
		if(testEnv==null) {
			System.out.println("Running on prod env");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {	
			try {
					switch(testEnv.toLowerCase()){
					case "qa":
						ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
						break;
					case"dev":
						ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
						break;
					case "uat":
						ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
						break;
					case "stage":
						ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
						break;
					default:
						System.out.println("pass the right environment variable");
						break;
					}	
				}catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		try {
				properties.load(ip);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/*
	 * take screenshot
	 */
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}
