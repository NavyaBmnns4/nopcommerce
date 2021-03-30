package com.nopcommerce.pageObjects;

  import java.util.concurrent.TimeUnit;
  
  import org.openqa.selenium.By; 
  import org.openqa.selenium.JavascriptExecutor;
  import org.openqa.selenium.WebDriver; 
  import org.openqa.selenium.WebElement;
  import org.openqa.selenium.support.ui.Select;
  
  public class AddcustomerPage { 
	  WebDriver driver; 
	  WebElement listitem;
  
  public AddcustomerPage(WebDriver driver) 
  { 
  this.driver=driver;
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
  } 
  By lnkCustomers_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]"); 
  By lnkCustomers_menuitem=By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
  
  By btn_AddNew=By.xpath("//a[normalize-space()='Add new']");
  
  By txt_email=By.xpath("//input[@id='Email']");
  By txt_password=By.xpath("//input[@id='Password']");
  By txt_Firstname=By.xpath("//input[@id='FirstName']"); 
  By txt_Lastname=By.xpath("//input[@id='LastName']");
  
  By rdbtn_male= By.xpath("//input[@id='Gender_Male']"); 
  By rdbtn_female=By.xpath("//input[@id='Gender_Female']"); 
  By txt_DOB=By.xpath("//input[@id='DateOfBirth']");
  
  By txt_cmpnyname=By.xpath("//input[@id='Company']");
  
  By lstbox_customerroles=By.xpath("//div[@class='input-group-append input-group-required']//div[@role='listbox']"); 
  By lstbox_Administrators=By.xpath("//li[normalize-space()='Administrators']");
  By lstbox_ForumModerators=By.xpath("//li[normalize-space()='Forum Moderators']");
  By lstbox_Guests=By.xpath("//li[normalize-space()='Guests']"); 
  By lstbox_Registered=By.xpath("//li[normalize-space()='Registered']"); 
  By lstbox_Vendors=By.xpath("//li[contains(text(),'Vendors')]");
  
  By drpmgrOfVendor=By.id("VendorId");
  
  By txt_Admincomment=By.xpath("//textarea[@id='AdminComment']"); 
  By btn_save=By.xpath("//button[@name='save']");
  By txtmsg=By.xpath("//div[@class='alert alert-success alert-dismissable']");
  
  //ActionMethods 
  public void ClickonCustomersmenu() {
  driver.findElement(lnkCustomers_menu).click(); 
  } 
  public void ClickonCustomersmenuitem() 
  {
  driver.findElement(lnkCustomers_menuitem).click(); 
  }
  public void ClickonAddnewCustomer() { driver.findElement(btn_AddNew).click(); 
  } 
  public void setemail(String email) 
  { 
	  driver.findElement(txt_email).sendKeys(email);
  } 
  public void setpwd(String pwd) 
  {
  driver.findElement(txt_password).sendKeys(pwd); 
  } 
  public void setfirstname(String fname) 
  {
  driver.findElement(txt_Firstname).sendKeys(fname); 
  } 
  public void setlastname(String lname)
  { 
	  driver.findElement(txt_Lastname).sendKeys(lname);
  } 
  public void setgender(String gndr) 
  { 
	  if(gndr.equals("Male"))
	  {
  driver.findElement(rdbtn_male).click(); 
  } 
	 else if(gndr.equals("Female"))
	  {
  driver.findElement(rdbtn_female).click(); 
  } 
	  } 
  public void setdob(String date)
  {
	  driver.findElement(txt_DOB).sendKeys(date); 
	  } 
  public void
  setcompanyname(String cname) 
  {
  driver.findElement(txt_cmpnyname).sendKeys(cname);
  } 
  public void setcustomerroles(String lstoption) throws InterruptedException
  {
  driver.findElement(lstbox_customerroles).click(); 
  Thread.sleep(3000);
  
  if(lstoption.equals("Registered")) 
  {
  listitem=driver.findElement(lstbox_Registered); 
  } 
  else if(lstoption.equals("Administrators")) 
  {
  listitem=driver.findElement(lstbox_Administrators); 
  }
  else if(lstoption.equals("ForumModerators"))
  {
  listitem=driver.findElement(lstbox_ForumModerators); 
  } 
  else if(lstoption.equals("Vendors")) 
  {
  listitem=driver.findElement(lstbox_Vendors); 
  } 
  else if(lstoption.equals("Guests")) 
  {
  driver.findElement(By.xpath("//span[normalize-space()='Registered']"));
  listitem=driver.findElement(lstbox_Guests); 
  } 
  
  else 
  {
  listitem=driver.findElement(lstbox_Guests); 
  } //listitem.click(); if it doesn't work then use JS
 
  JavascriptExecutor js=(JavascriptExecutor) driver;
  js.executeScript("arguments[0].click();", listitem); 
  } 
  public void setmngerofvendor(String option) 
  { 
  Select s=new Select(driver.findElement(drpmgrOfVendor)); 
  s.selectByVisibleText(option);
  }
  public void setadmincomment(String comments) 
  {
  driver.findElement(txt_Admincomment).sendKeys(comments); 
  } 
  public void clicksave() 
  { 
	  driver.findElement(btn_save).click(); 
  }
  
  public boolean Verifyconfirmationmsg() 
  { 
	  String s1=driver.findElement(txtmsg).getText();
  if(s1.contains("The new customer has been added successfully")) 
  { 
	  return true; 
	  } 
  else 
  { 
return false; 
} 
  }
  
  }
 