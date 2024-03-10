package alerts;

import java.io.File;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;


public class JavaSript  {
	
	public static WebDriver driver;
	static String file=(System.getProperty("user.dir")+"/src/test/resources/inputs.xlsx");
	
	public static WebDriver createDriver()
	{
		
		driver=driverSetup.getWebDriver();	//creating the driver and return the driver
		driver.get("https://www.stqatools.com/demo/Alerts.php");	//launch the browser 
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public void alertBox() throws Exception{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		//adding wait
		WebElement basicalert=driver.findElement(By.id("btnAlert"));		
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", basicalert);
		//Thread.sleep(3000);
		basicalert.click();														//clicking basic alert
		WebElement alert1box=driver.findElement(By.id("ezAlerts-message"));
		Thread.sleep(3000);
		if(alert1box.isDisplayed()) {
			System.out.println(alert1box.getText());
			driver.findElement(By.xpath("//button[text()='Ok']")).click(); //accepting the alert and used cssSelectors
		}
	}
	public void loginPage() throws Exception {
		driver.navigate().to("https://stqatools.com/demo/");		//navigating to https://stqatools.com/demo/ browser
		String username=excelUtils.getCellData(file,"Sheet1", 0, 0);	//
		driver.findElement(By.id("username")).sendKeys(username);
		String password= excelUtils.getCellData(file,"Sheet1", 0, 1);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();		//click on login button
		driver.findElement(By.id("home-button")).click();		//click on go to home button
	}
	
	public void submitForm() throws Exception 
	  {
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));	//adding wait
		    JavascriptExecutor js = ((JavascriptExecutor)driver);
		    WebElement name=driver.findElement(By.name("name"));		//
		    String fname=excelUtils.getCellData(file,"Sheet1", 1, 0);
		    js.executeScript("arguments[0].value=arguments[1]",name,fname);
		    WebElement address=driver.findElement(By.id("address"));
		    String faddress=excelUtils.getCellData(file,"Sheet1", 2, 0);
		    js.executeScript("arguments[0].value=arguments[1]",address,faddress);
		    WebElement rb_male=driver.findElement(By.id("male"));
		    js.executeScript("arguments[0].click();",rb_male);
		    WebElement checkbox=driver.findElement(By.id("reading"));
		    js.executeScript("arguments[0].click();",checkbox);
		    WebElement country=driver.findElement(By.xpath("//option[@value='India']"));
		    country.click();
		    js.executeScript("arguments[0].value='india'",country);
		    WebElement city=driver.findElement(By.xpath("//option[@value='Delhi']"));
		    city.click();
		    js.executeScript("arguments[0].value='Delhi'",city);
		    //String date="09/13/2002";
		    WebElement dob=driver.findElement(By.id("dob"));
		    String date=excelUtils.getCellData(file,"Sheet1", 3, 0);
		    //js.executeScript("arguments[0].value=arguments[1]", dob, date);
		    dob.sendKeys(date);

		    WebElement submit=driver.findElement(By.xpath("//*[@type='submit']"));
		    js.executeScript("arguments[0].click();",submit);		//click on submit
		    Thread.sleep(2000);
		    js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");		//window scrollup
	  }
	public void takeScreenshot() throws Exception
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		//WebElement ss=driver.findElement(By.xpath("//*[@id=\"registration-form\"]"));	//take screenshot
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File("C:\\Users\\2303629\\eclipse-workspace\\miniProject\\src\\test\\resources\\screenshots.png");
		FileUtils.copyFile(src, trg);
	}
		
	public static void closeBrowser()
	{
		driver.quit();
	}
		    
		    
	
	public static void main(String[] args) throws Exception
	{
		JavaSript obj=new JavaSript();
		createDriver();
		obj.alertBox();
		obj.loginPage();
		obj.submitForm();
		obj.takeScreenshot();
		closeBrowser();
	}

}
 


	   
	
