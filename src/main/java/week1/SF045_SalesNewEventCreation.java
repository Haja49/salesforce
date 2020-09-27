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

public class SF045_SalesNewEventCreation {

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
		
		
		//8) Click on today's date in the calendar
		Calendar cal = Calendar.getInstance();
		Date tday = cal.getTime();//get current date and time
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//change format as required
		String selDate= formatter.format(tday);
		String[] dvalue = selDate.split("/");
		System.out.println(dvalue[0]);
		String dval = dvalue[0];
		String date_xpath = "//span[text()='"+dval+"']";
		driver.findElementByXPath(date_xpath).click();
		
		//9) Click on the first cell between 10 AM & 11 AM in the current day
		
		String timeslot = driver.findElementByXPath("//span[text()='10am']").getText();
		System.out.println("Timeslot selected:" + timeslot);
		driver.findElementByXPath("//strong[text() = 'SUN 27']").click();
		driver.findElementByXPath("//span[text() = 'All-Day Event']").click(); //Uncheck the checkbox to enter time values
		
		// Select 10 AM 
		driver.findElementByXPath("(//input[@class='slds-input slds-combobox__input'])[3]").sendKeys(Keys.SPACE);
		WebElement start_time = driver.findElementByXPath("//span[@title='10:00 AM']");
		JavascriptExecutor selecttime = (JavascriptExecutor)driver;
		selecttime.executeScript("arguments[0].scrollIntoView(true)", start_time);
		selecttime.executeScript("arguments[0].click();", start_time);
		
		// Select 11 AM
		driver.findElementByXPath("(//label[text()='Time'])[2]//following::div/input").sendKeys(Keys.SPACE);
		WebElement to_time = driver.findElementByXPath("(//span[@title='11:00 AM'])[2]");
		selecttime.executeScript("arguments[0].scrollIntoView(true)", to_time);
		selecttime.executeScript("arguments[0].click();", to_time);
		
			
		// 10) Select Subject as 'Email'	
		driver.findElementByXPath("(//input[@class='slds-input slds-combobox__input'])[2]").sendKeys("Email");
		
		// 11) Enter the description as 'Specimen'
		driver.findElementByTagName("textarea").sendKeys("Test Description");
		// 12) Click on Save
		driver.findElementByXPath("//button[@title = 'Save']").click();
		
		// 13) Verify Event Created
		String eventsub = driver.findElementByXPath("//a[@class='subject-link']").getText();
		if(eventsub.equals("Email"))
			System.out.println("Event Created Successfully:"+ eventsub );
		else
			System.out.println("Event Creation is not successful" );
		
		driver.close();

	}

}
