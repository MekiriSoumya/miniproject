package alerts;

import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class  driverSetup {
	
		public static WebDriver driver;
		public static WebDriver getWebDriver()
		{
			System.out.println("Enter browser name: ");
			Scanner sc= new Scanner(System.in);
			String browser = sc.next();
			sc.close();
			if(browser.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver =new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("Edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			}
			return driver;
		}
}
