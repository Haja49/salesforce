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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TCID_CreateOpportunity {

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		 ChromeOptions ops = new ChromeOptions();
	        ops.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(ops);
		
		//1.Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();
		
		//2. Click on toggle menu button from the left corner
		driver.findElementByClassName("slds-icon-waffle").click();
		
		//3. Click view All and click Sales from App Launcher
		driver.findElementByXPath("//button[text()='View All']").click();
		driver.findElementByXPath("//p[text()='Sales']").click();
		
		//4. Click on Opportunity tab 
			
		WebElement opplink = driver.findElementByXPath("//span[text()='Opportunities']");
		JavascriptExecutor clickelement = (JavascriptExecutor)driver;
		clickelement.executeScript("arguments[0].click();", opplink);
		//driver.findElementByXPath("(//span[@class='slds-truncate'])[3]").click();
		
		//5. Click on New button
		driver.findElementByXPath("//div[@title='New']").click();
		
		//6. Choose Close date as Tomorrow Date
		Calendar cal = Calendar.getInstance();
		Date tday = cal.getTime();//get current date and time
		cal.add(Calendar.DAY_OF_YEAR, 1);//add req number of days
		Date tmrw = cal.getTime();//get date and time value after increment
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//change format as required
		String selDate= formatter.format(tmrw);
		String[] dvalue = selDate.split("/");
		System.out.println(dvalue[0]);
		String dval = dvalue[0];
		String date_xpath = "//span[text()='"+dval+"']";
		
		driver.findElementByXPath("//input[@class=' input']").click();
		driver.findElementByXPath(date_xpath).click();
		
		//7. Click on save
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
		//8. Verify the Alert message (Complete this field) displayed for Name and Stage
		String errmsg = driver.findElementByXPath("//li[text()='These required fields must be completed: Opportunity Name, Stage']").getText();
		if(errmsg.equals("These required fields must be completed: Opportunity Name, Stage"))
			System.out.println(errmsg);
		else
			System.out.println("Incorrect Message");
			
		//Fill REquired fields and Click on save
		driver.findElementByXPath("(//input[@class=' input'])[2]").sendKeys("Opp_D");
		driver.findElementByXPath("//a[@class='select']").sendKeys(Keys.SPACE);
		driver.findElementByXPath("//a[@title= 'Prospecting']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		Thread.sleep(6000);
		driver.quit();
		
	
		
	}

}
