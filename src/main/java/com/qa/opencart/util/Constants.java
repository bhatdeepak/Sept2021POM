package com.qa.opencart.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String LOGIN_PAGE_URL = "https://demo.opencart.com/index.php?route=account/login";
	public static final int DEFAULT_TIME_OUT = 5;
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_HEADER = "Your Store11";
	public static final String LOGIN_ERROR_MSG = "No match for E-Mail Address and/or Password";
	public static final String ACCOUNT_CREATION_SUCCESS = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME ="registrationData";
	
	public static List<String> getAccPageSecList(){
		List<String> eleList = new ArrayList<String>();
		eleList.add("My Account");
		eleList.add("My Orders");
		eleList.add("My Affiliate Account");
		eleList.add("Newsletter");
		
		return eleList;
	}
}
