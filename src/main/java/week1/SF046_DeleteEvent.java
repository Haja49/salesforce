package week1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF046_DeleteEvent {

	public static void main(String[] args) throws Exception {
		
		WebDriverManager.chromedriver().setup();
		 ChromeOptions ops = new ChromeOptions();
	        ops.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(ops);
		
		//1) Launch the app
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//2) Click Login
		driver.findElementById("Login").click();
		
		//3) Login with the credentials
		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();
		
		//4) Click on the App Launcher Icon left to Setup
		driver.findElementByClassName("slds-icon-waffle").click();
		
		//5) Click on View All
		driver.findElementByXPath("//button[text()='View All']").click();
		
		//6) Click on Sales
		driver.findElementByXPath("//p[text()='Sales']").click();
		
		//7) Click on Calendar
		WebElement cal_link = driver.findElementByXPath("(//span[text()='Calendar'])");
		JavascriptExecutor clickelement = (JavascriptExecutor)driver;
		clickelement.executeScript("arguments[0].click();", cal_link);
		
		//8) Hover over 'Email' event that is between 10 AM to 11 AM
		WebElement eventElement = driver.findElementByXPath("//a[text()='Email']");
		Actions action = new Actions(driver);
		action. moveToElement(eventElement).perform();
		
		//9) Click on 'Delete'
		driver.findElementByXPath("//a[@title='Delete']").click();
		Thread.sleep(2000);
		
		//10) Click on 'Delete'
		driver.findElementByXPath("//span[text()='Delete']").click();
		Thread.sleep(2000);
		
		//11) Verify event deleted
		List<WebElement> events = driver.findElementsByXPath("//a[text()='Email']");
		if(events.isEmpty())
			System.out.println("Event deleted successfully." );
		else
			System.out.println("Event is not deleted successfully." );
			
		driver.close();

	}

}
