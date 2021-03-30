package com.nopcommerce.utilities;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp=new SimpleDateFormat("YYYY.mm.dd.HH.mm.ss").format(new Date()); //Generates timeStamp of report
		repName="Test-Report-"+timeStamp+".html";
	
		htmlReporter=new ExtentHtmlReporter(".\\reports\\"+repName);//specify location of the report
	
		htmlReporter.config().setDocumentTitle("nopCommerce Automation Report");
		htmlReporter.config().setReportName(repName);
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Navya");
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestContext().getName()); // create new entry in th report
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestContext().getName());    // create new entry in th report
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		String screenshotpath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		try
		{
			test.addScreenCaptureFromPath(screenshotpath); // adding screen shot
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Failed");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();	
		/*try {
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 // Create the email message
		  ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googlemail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("navya.bmnns4@gmail.com", "pwd"));
		  email.setSSLOnConnect(true);
		  email.setFrom("navya.bmnns4@gmail.com");   //Sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report....");
		  email.addTo("budumanavya001@gmail.com");   //Receiver
		  email.attach(url, "extent report", "please check report...");
		  email.send();   // send the email
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		 
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
}
