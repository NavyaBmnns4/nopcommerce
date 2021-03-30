package com.nopcommerce.testCases;

import java.io.IOException; 
import org.testng.Assert; import org.testng.annotations.Test;
import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage; 
import com.nopcommerce.testBase.BaseClass;
  
public class TC_AddCustomerTest_003 extends BaseClass {
  
  @Test public void addnewcust() throws InterruptedException, IOException 
  {
  logger.info("********* starting TC_AddCustomerTest_003 *************");
  
  driver.get(ConfigPropObj.getProperty("baseURL")); 
  LoginPage lp=new LoginPage(driver);
  lp.setUsername(ConfigPropObj.getProperty("Useremail"));
  lp.setPassword(ConfigPropObj.getProperty("password")); 
  lp.clickLogin();
  Thread.sleep(3000);
  
  logger.info("*********Adding new customer *************");
  
  AddcustomerPage cp=new AddcustomerPage(driver); 
  cp.ClickonCustomersmenu();
  cp.ClickonCustomersmenuitem(); 
  cp.ClickonAddnewCustomer();
  cp.setemail("Test1@gmail.com"); 
  cp.setpwd("Test@1235");
  cp.setfirstname("Test2"); 
  cp.setlastname("ABCD"); 
  cp.setgender("Female");
  cp.setdob("04/02/1999"); 
  cp.setcompanyname("Testing");
  cp.setcustomerroles("Vendors"); 
  cp.setmngerofvendor("Vendor 1");
  cp.setadmincomment("Testing is done....");
  cp.clicksave(); 
  Thread.sleep(3000);
  
  // validation 
  if (cp.Verifyconfirmationmsg()) 
  {
  logger.info("***************  Customer added succesfully *********** ");
  Assert.assertTrue(true);
  
  }
  else 
  {
  logger.error("*************** Customer Not added succesfully *********** ");
  CaptureScreen(driver,"addNewCustomer"); Assert.assertTrue(false); }
  logger.info("***************   TC_AddCustomerTest_003 Finished  *********** "); 

  }
  
  }
  
  
 