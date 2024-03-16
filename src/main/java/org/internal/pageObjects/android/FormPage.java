package org.internal.pageObjects.android;

import org.internal.utils.AndroidActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	AndroidDriver driver;
	
	
	//Constructor to get driver
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleOption;
	
	@AndroidFindBy(xpath="//android.widget.Toast")
	private WebElement toastMessage;

	@AndroidFindBy(id="android:id/text1")
	private WebElement countryDropdown;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopButton;
	
	
	
	
	public void setNameField(String name) 
	{
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void selectGender(String gender) 
	{
		if(gender.contains("female")) {
			femaleOption.click();
		}
		else {
			maleOption.click();
		}
	}
	
	public void validateToastMessage(String expectedMessage) 
	{
		String toastMessageText = toastMessage.getAttribute("name");
		Assert.assertEquals(toastMessageText, expectedMessage);
	}
	
	public void selectCountry(String countryName) {
		countryDropdown.click();
		ScrollToTextAction(countryName);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text = '"+countryName+"']")).click();
	}
	
	public ProductsCatalogue clickLetsShopButton() {
		letsShopButton.click();
		return new ProductsCatalogue(driver);	
	}
	
	public void setActivity() {
		//com.androidsample.generalstore/com.androidsample.generalstore.MainActivity
		((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
	}
	
}
