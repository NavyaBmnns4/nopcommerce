package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

//This class contains only common reusable methods

public class BaseClass {
	
	public Properties ConfigPropObj;
	public WebDriver driver;
	
	public Logger logger=LogManager.getLogger(this.getClass()); //Log4j2
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException
	{
		//Load config.properties file
		ConfigPropObj= new Properties();
		FileInputStream Configfile=new FileInputStream(".\\resources\\config.properties");
		ConfigPropObj.load(Configfile);
		//end of loading config.properties file
		
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
	public void CaptureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"\\Screenshots\\"+tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
	}
	public String randomestring() {
		String generatedString1=RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
		
	}
	public int randomeNum() 
	{
		String generatedString2=RandomStringUtils.randomNumeric(4);
		return (Integer.parseInt(generatedString2));
		
	}
}
