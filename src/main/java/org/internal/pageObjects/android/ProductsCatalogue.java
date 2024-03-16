package org.internal.pageObjects.android;

import java.util.List;

import org.internal.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsCatalogue extends AndroidActions{

	AndroidDriver driver;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCart;
	//List is used because here we are replicating 'driver.findElements'. SO, we
	//are expecting a list of elements
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	
	
	//constructor
 	public ProductsCatalogue(AndroidDriver driver) {
 		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	

	public void addItemToCartByIndex(int index) {
		addToCart.get(index).click();
	}
	
	
	
	public CartPage clickOnCart() {
		cartButton.click();
		return new CartPage(driver);
	}


}

