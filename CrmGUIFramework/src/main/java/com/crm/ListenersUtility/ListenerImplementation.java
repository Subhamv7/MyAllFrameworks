package com.crm.ListenersUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.BaseTest.BaseClass;
import com.crm.generic.webDriverUtility.UtilityClassObject;

public class ListenerImplementation implements ITestListener, ISuiteListener {
	public static ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date().toString().replace(" " , "_").replace(":","_");

		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRMReport");
		spark.config().setTheme(Theme.STANDARD);

		//add Environment information & Create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows10");
		report.setSystemInfo("browser", "Chrome-121");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}



	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("=====" + result.getMethod().getMethodName() + "====");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"====>STARTED<=======");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("=====" + result.getMethod().getMethodName() + "==End==");
		test.log(Status.PASS, result.getMethod().getMethodName()+"====>Completed<=======");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
			String tsName = result.getMethod().getMethodName();
			TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;// [driver]
			// [Driver is present baseClass we can't invoke the methods in baseclass because
			// those are config methods
			// and we can't make driver as static because it will not support in parallel
			// execution]
			String src = ts.getScreenshotAs(OutputType.BASE64);
			String time = new Date().toString().replace(" " , "_").replace(":","_");
			test.addScreenCaptureFromBase64String(src, tsName+"_"+time);
			test.log(Status.FAIL, result.getMethod().getMethodName()+"====>Failed<=======");
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName()+"====>Skipped<=======");
	}

}
