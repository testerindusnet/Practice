package testcase;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


public class Text2Insure {
	
	public WebDriver driver;
  
	@BeforeClass
	  public void setUp() {
		
		 System.setProperty("webdriver.chrome.driver","E:\\server\\chromedriver\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().deleteAllCookies();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		 driver.get("https://dev.text2insure.co.uk/backmeup/public_html/");
		
		
	  }	
	
  @Test
  public void registrationTest() throws Exception {
	  
	  driver.findElement(By.linkText("Get Insured")).click();
	  driver.findElement(By.id("nextBut")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.id("nextBut")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.id("staffWarningFormBtn")).click();
	  Select title=new Select(driver.findElement(By.id("title")));
	  title.selectByValue("Mr");
	  driver.findElement(By.id("firstName")).sendKeys("Nil");
	  driver.findElement(By.id("surname")).sendKeys("Anderson");
	  Select dobday=new Select(driver.findElement(By.id("dobDay")));
	  dobday.selectByValue("10");	  
	  Select dobmonth=new Select(driver.findElement(By.id("dobMonth")));
	  dobmonth.selectByValue("10");
	  Select dobyear=new Select(driver.findElement(By.id("dobYear")));
	  dobyear.selectByValue("1970");
	  driver.findElement(By.id("email")).sendKeys("nil321@mailinator.com");
	  driver.findElement(By.id("verifyByEmail")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//input[@value='Ok']")).click();
	  
	//open a new tab
	  ((JavascriptExecutor)driver).executeScript("window.open('https://www.mailinator.com/');");
	  
	  // get the information of total open tabs
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  
	// shift to new tab
	  driver.switchTo().window(tabs.get(1));
	 
	  Thread.sleep(3000);
	  driver.findElement(By.id("inboxfield")).sendKeys("nil321@mailinator.com");
	  Thread.sleep(4000);
	  driver.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
	  driver.findElement(By.xpath("//div[contains(text(),'Back Me Up verification code')]")).click();
	  driver.switchTo().frame(driver.findElement(By.id("msg_body")));
	  
	  String verifytext=driver.findElement(By.xpath("//td/strong")).getText();
	  
	
	   driver.close();
	   
	   // close the new  tab
	   
	   
	   //switch to  parent tab
	   driver.switchTo().window(tabs.get(0));	   
	   driver.findElement(By.id("verifyCode")).sendKeys(verifytext);
	  
	
  }
  

  @AfterClass
  public void afterClass() {
  }

}
