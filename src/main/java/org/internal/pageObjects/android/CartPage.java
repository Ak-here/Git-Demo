package org.internal.pageObjects.android;

import java.time.Duration;
import java.util.List;

import org.internal.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	AndroidDriver driver;
	double totalPrice = 0;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	private WebElement pageHeader;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productsPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement emailSubscription;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndConditions;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
	private WebElement termsAndConditionsPopupTitle;
	
	@AndroidFindBy(id="android:id/message")
	private WebElement termsAndConditionsPopupMessage;
	
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement termsAndConditionsPopupCloseButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;
	
	
	public void waitForCartPageToLoad(String textToBeVisible) {
		waitForElementToAppear(pageHeader, textToBeVisible, driver);
	}
	
	public double productsPriceTotal() {
		int count = productsPrice.size();
		
		for(int i=0; i<count; i++) 
		{	
			
			String priceString = productsPrice.get(i).getText();
			Double price = getFormattedAmount(priceString.substring(1));
			System.out.println(price);
			totalPrice+=price;
		}
		return totalPrice;
	}
	
	public void validateTotalAmount(double productsPriceSum) {
		String finalPriceString = totalAmount.getText();
		double finalPrice = getFormattedAmount(finalPriceString.substring(1));
		
		//Validation
		Assert.assertEquals(productsPriceSum, finalPrice);
	}
	
	public void emailSubscriptionCheckboxClick()
	{
		emailSubscription.click();
	}
	
	public void openTermsAndConditions() {
		LongPressAction(termsAndConditions);
	}
	
	public void validateTAndCPromptTitle(String titleText) {
		String tAndCPopupTitle = termsAndConditionsPopupTitle.getText();
		Assert.assertEquals(tAndCPopupTitle, titleText);
	}
	
	public void validateTAndCPromptMessage(String messageText) {
		String tAndCPopupActualMessage = termsAndConditionsPopupMessage.getText();
		Assert.assertEquals(tAndCPopupActualMessage, messageText);
	}
	
	public void closeTermsAndConditions() {
		termsAndConditionsPopupCloseButton.click();
	}
	
	public void clickOnProceedButton() {
		proceedButton.click();
	}

}
