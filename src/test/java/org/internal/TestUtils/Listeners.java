package org.internal.TestUtils;

import java.io.IOException;

import org.internal.utils.AppiumUtils;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener{
	
	AppiumDriver driver;
	
	//test provides the test details to the report
	ExtentTest test;
	
	//extent is responsible to add test in the report
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	
	public void onTestStart(org.testng.ITestResult result) 
	{
	test = extent.createTest(result.getMethod().getMethodName());
	}
    
	public void onTestSuccess(org.testng.ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
		
		//Getting driver from test method class > Base Test class
		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// Adding screenshopt to the report withn the method name
		try {
		test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
  
	public void onTestFailure(org.testng.ITestResult result)
	{
		
		//Screenshot
		
		// Adds the console output in report
		test.fail(result.getThrowable());
		
		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
		test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
  
	public void onTestSkipped(org.testng.ITestResult result)
	{
		
	}
  
	public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult result)
	{
		
	}
  
	public void onTestFailedWithTimeout(org.testng.ITestResult result)
	{
		
	}
  
	public void onStart(org.testng.ITestContext context)
	{
		
	}
  
	public void onFinish(org.testng.ITestContext context)
	{
		//Signals ExtentReport that the execution is finished, generate report now.
		extent.flush();
	}
  
}
