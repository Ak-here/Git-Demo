package org.internal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.internal.TestUtils.BaseTest;
import org.internal.pageObjects.android.CartPage;
import org.internal.pageObjects.android.ProductsCatalogue;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;

public class eCommerce_Data_Driven_Testing_Via_DataProvider_Example extends BaseTest
{	
	//In this we are driving multiple data sets to a test. Basically testing for different sets of data.

	@Test(dataProvider="getData", groups = {"Smoke"})
	public void LoginFormErrorValidation(HashMap<String, String> input) throws InterruptedException 
	{
		
		//Selecting Female radio button
		formPage.selectGender(input.get("name"));
		
		//selecting Country "Belarus" from country dropdown
		formPage.selectCountry(input.get("country"));
		
		//Clicking on "Lets Shop" button
		formPage.clickLetsShopButton();		
		
		//Validating form toast message
		formPage.validateToastMessage(input.get("formToastMessage"));
		
		//Entering name on text field
		formPage.setNameField(input.get("name"));
		
		//Clicking on "Lets Shop" button
		formPage.clickLetsShopButton();
	}
	
	
	@BeforeMethod (alwaysRun = true)
	public void preSetup() 
	{
		formPage.setActivity();
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{ 
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\internal\\testData\\eCommerce.json");
		//return new Object[][] {{"female", "Belarus", "Please enter your name", "Ritu"}, {"male", "Austria", "Please enter your name", "Rakesh"}};
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	
	}
}
