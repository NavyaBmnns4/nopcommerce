package com.nopcommerce.testCases;

//This is login test
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass {


@Test
public void LoginTest() throws IOException
{
	
	logger.info("****Starting this testcase****");
	
	driver.get(ConfigPropObj.getProperty("baseURL"));
	
	logger.info("****Providing login details****");
	
	LoginPage lp=new LoginPage(driver); 
	lp.setUsername(ConfigPropObj.getProperty("Useremail"));
	lp.setPassword(ConfigPropObj.getProperty("password"));
	lp.clickLogin();
	
	//validation
	logger.info("****Validating login ****");
	
	String exp_title="Dashboard / nopCommerce administration";
	String act_title=driver.getTitle();
	if(exp_title.equals(act_title))
	{
		logger.info("****Login is successful****");
		Assert.assertTrue(true);
	}
	else
	{
		logger.error("****Login is unsuccessful****");
		CaptureScreen(driver,"LoginTest");
		Assert.assertTrue(false);
	}
}




}
