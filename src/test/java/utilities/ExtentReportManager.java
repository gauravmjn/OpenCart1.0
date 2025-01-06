package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repname;
	public void onStart(ITestContext testContext) {
		/*
		SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");//here we created the format of our date and stored it in df
		Date dt = new Date();//here we declare the date in java its a java class
		String currentdatetimestamp=df.format(dt);
		*/
		//or we can make this in single line 
		String datetimestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
		repname="Test report-"+datetimestamp+".html";
		sparkreporter= new ExtentSparkReporter(".\\reports\\"+repname);//specify location of the report
		
		sparkreporter.config().setDocumentTitle("OpenCart Automation Report");//Title of the report
		sparkreporter.config().setReportName("OpenCart Functional Testing");//name of the report
		sparkreporter.config().setTheme(Theme.DARK);//setting theme of the report
		
		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Submodule", "customers");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
		
	}
	public void onTestSuccess(ITestResult result) 
	{
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS,result.getName()+"got Successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+"Got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		//taking screen shot of the failed test
		try{
		String imgPath= new BaseClass().captureScreen(result.getName());
		//System.out.println(imgPath);
		test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
	}
	 public void onTestSkipped(ITestResult result) 
	 {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP,result.getName()+"Got Skipped");
		 test.log(Status.INFO,result.getThrowable().getMessage());
		 
	 }
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
		String filepathExtentReport=System.getProperty("user.dir")+".\\reports\\"+repname;
		File extentReport = new  File(filepathExtentReport);
		try {
		Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		

}
