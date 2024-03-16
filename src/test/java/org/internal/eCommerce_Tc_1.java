package org.internal;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.internal.TestUtils.BaseTest;
import org.internal.pageObjects.android.CartPage;
import org.internal.pageObjects.android.FormPage;
import org.internal.pageObjects.android.ProductsCatalogue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_Tc_1 extends BaseTest{

	@Test
	public void EndToEndTest() throws InterruptedException {
		
		//Selecting Female radio button
		formPage.selectGender("female");
		
		//selecting Country "Belarus" from country dropdown
		formPage.selectCountry("Belarus");

		
		//Clicking on "Lets Shop" button
		formPage.clickLetsShopButton();		
		
		//Validating form toast message
		formPage.validateToastMessage("Please enter your name");
		
		//Entering name on text field
		formPage.setNameField("Ritu");
		
		//Clicking on "Lets Shop" button
		ProductsCatalogue prodCatalogue = formPage.clickLetsShopButton();
			
		//Opening Cart
		prodCatalogue.clickOnCart();
		
		//Validating the toast message text
		formPage.validateToastMessage("Please add some product at first");
		
		
		prodCatalogue.addItemToCartByIndex(0);
		prodCatalogue.addItemToCartByIndex(1);
		
		//Opening Cart
		CartPage cartPage = prodCatalogue.clickOnCart();

		cartPage.waitForCartPageToLoad("Cart");
		
		//Validating Item Price sum with final amount
		double productsPriceSum = cartPage.productsPriceTotal();
		
		cartPage.validateTotalAmount(productsPriceSum);
		
		//Checking the checkbox
		cartPage.emailSubscriptionCheckboxClick();
		
		//Long press on Terms and Conditions 
		cartPage.openTermsAndConditions();
		
		//Validating popup title
		cartPage.validateTAndCPromptTitle("Terms Of Conditions");
		
		//Validating popup message
		cartPage.validateTAndCPromptMessage("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
		
		//Clicking on Close button
		cartPage.closeTermsAndConditions();
		
		//Clicking on Visit to website button
		cartPage.clickOnProceedButton();
		
	}
	
	@Test
	public void EndToEndTestPositiveFlow() throws InterruptedException {
		
		//Entering name on text field
		formPage.setNameField("Ritu");
		
		//Selecting Female radio button
		formPage.selectGender("female");
		
		//selecting Country "Belarus" from country dropdown
		formPage.selectCountry("Armenia");	
		
		//Clicking on "Lets Shop" button
		ProductsCatalogue prodCatalogue = formPage.clickLetsShopButton();		
		
		prodCatalogue.addItemToCartByIndex(0);
		prodCatalogue.addItemToCartByIndex(1);
		
		//Opening Cart
		CartPage cartPage = prodCatalogue.clickOnCart();

		cartPage.waitForCartPageToLoad("Cart");
		
		//Checking the checkbox
		cartPage.emailSubscriptionCheckboxClick();
		
		//Long press on Terms and Conditions 
		cartPage.openTermsAndConditions();
	
		//Clicking on Close button
		cartPage.closeTermsAndConditions();
		
		//Clicking on Visit to website button
		cartPage.clickOnProceedButton();
		
	}
	
	@BeforeMethod (alwaysRun = true)
	public void preSetup() 
	{
		formPage.setActivity();
	}
}
