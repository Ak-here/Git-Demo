package org.internal.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumUtils {
	
	public AppiumDriverLocalService service;
	
	public double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount);
		return price;
	}
	
	public void waitForElementToAppear(WebElement ele, String textToBeVisible, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.attributeContains(ele ,"text", textToBeVisible));
	}
	
	//Method to read data from Json file
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException{
		
		
		//jsonFilePath = System.getProperty("user.dir")+"\\src\\test\\java\\org\\internal\\testData\\eCommerce.json"
		//Step 1: Convert Json file content to Json String
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		
		//Step 2: Converting Json string to Hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>(){
			
		});
		
		return data;
		
	}
	
	
	//Method to start the server
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		//"C://Users//aksha//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"
		service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//aksha//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")).withIPAddress(ipAddress).usingPort(port).build();
				
		//Starting the appium server
		service.start();
		
		return service;
	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException 
	{
		//Taking screenshot virtually
		File source = driver.getScreenshotAs(OutputType.FILE);
		
		//Specifying destination path where screenshot will be saved
		String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		
		//Copying screenshot from virtual to specified path
		FileUtils.copyFile(source, new File(destinationFile));
		
		return destinationFile;
	}
	
}
