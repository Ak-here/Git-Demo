package org.internal.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.internal.pageObjects.android.FormPage;
import org.internal.utils.AndroidActions;
import org.internal.utils.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class BaseTest extends AppiumUtils
{

	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public FormPage formPage;
	
	
	@BeforeTest (alwaysRun = true)
	public void ConfigureAppium() throws IOException 
	{	
		
		//
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\internal\\resources\\data.properties");
		prop.load(fis);
		
		//prop.getProperty("ipAddress") here we are fetching ipAddress from data.properties file 
		//String ipAddress = prop.getProperty("ipAddress");
		
		
		//Here we tweak our code to check if ipAddress is provided in mvn command if yes then use that, if no then get from data.properties file
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		int port = Integer.parseInt(prop.getProperty("port"));
		
		service = startAppiumServer(ipAddress, port);		
				
		//Creating object of UiAutomator2Options class through which capabilities can be provided.
		UiAutomator2Options options = new UiAutomator2Options();
				
		//Providing virtual device name
		options.setDeviceName("AndroidVirtualMachine_AK");
				
		//Changed for Ecommerce app testing
		//options.setApp("C://Users//aksha//eclipse-latest-workspace//Test//Appium//src//test//java//resources//General-Store.apk");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\org\\internal\\resources\\General-Store.apk");
		
		//Setting up chromedriver because it is required in hybrid app testing
		options.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\test\\java\\org\\internal\\resources\\chromedriver.exe");
		
		//Creating object of Android driver and passing Appium server url and UiAutomator2Options object as parameter
		//driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
		driver = new AndroidDriver(service.getUrl(), options);
		
		//Used Implicit wait here which means it'll wait for atmost 10 seconds for any element, if element is visible in 3 sec then it'll not wait for extra 7 sec. 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		//Creating object of form page so it automatically gets created within this initital method
		formPage = new FormPage(driver);
	
	}
	
	
	@AfterTest (alwaysRun = true)
	public void TearDown() 
	{
		//quitting the driver
		driver.quit();
		
		//closing the appium server
		service.stop();
	}
	
}
