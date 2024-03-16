package org.internal.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidActions extends AppiumUtils{

	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}
	
	public void LongPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),"duration", 2000));
	}
	
	public void ScrollToTextAction(String text) {

		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
	public void ScrollToEndAction() {
		boolean canScrollMore;
		
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
		}while(canScrollMore);
	}
	
	public void SwipeAction(WebElement ele, String direction) {
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
				"direction", direction,
				"percent", 0.20
				));
	}
	
	public void DragAndDropAction(WebElement source, int dropXCoordinate, int dropYCoordinate) {
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture",ImmutableMap.of(
				"elementId", ((RemoteWebElement) source).getId(),
				"endX", dropXCoordinate,
				"endY", dropYCoordinate
				));
	}
	
}
