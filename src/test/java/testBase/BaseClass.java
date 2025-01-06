package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BaseClass 
{
	public static WebDriver driver;
	public Properties p;
	
		@BeforeClass(groups={"sanity","regression","master"})
		@Parameters({"os","browser"})
		
		public void setup(String os,String br) throws IOException
		{
			FileReader file = new FileReader("./src//test//resources//config.properties");
			p=new Properties();
			
			p.load(file);
			if (driver == null) {
			    System.out.println("Driver initialization failed. Please check the setup.");
			} else {
			    System.out.println("Driver initialized successfully.");
			}

			
			if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			{
				DesiredCapabilities capabilities = new DesiredCapabilities();
				
				//os
				if(os.equalsIgnoreCase("Windows")) {
					capabilities.setPlatform(Platform.WIN10);
				}
				else if(os.equalsIgnoreCase("Linux"))
				{
					capabilities.setPlatform(Platform.LINUX);
				}
				else {
					System.out.println("no matching found");
					return;
				}
				//browser
				switch(br.toLowerCase()) {
				case "chrome": capabilities.setBrowserName("chrome");break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
				default : System.out.println("no matching found");return;
				}
				
				driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
				
			}
			
			if(p.getProperty("execution_env").equalsIgnoreCase("local"))
			{
				 System.out.println("Setting up Local WebDriver...");
				switch(br.toLowerCase()) 
				{
				case "chrome" : driver=new ChromeDriver();break;
				case "edge" : driver=new EdgeDriver();break;
				case "firefox" : driver=new FirefoxDriver();break;
				default: System.out.println("Invalid Browser name...."); return;
				
				}
			}
			driver.get(p.getProperty("appUrl1"));
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
			
		}
		 @AfterClass(groups={"sanity","regression","master"})
		 public void tearDown() {
			
			driver.quit();
			
		}
		
		public String randomString() 
		{
			
			String generatedString=RandomStringUtils.randomAlphanumeric(5);
			return generatedString;
		}
		
		public String randomNumeric(){
			String generateNumber=RandomStringUtils.randomNumeric(10);
			return generateNumber;
		}
		
		public String randomAlphaNumeric() 
		{
			
			String generatedString=RandomStringUtils.randomAlphanumeric(5);
			String generatedNumber=RandomStringUtils.randomNumeric(5);
			return (generatedString+generatedNumber);
		}
		
		public String captureScreen(String sname) {
			if (driver == null) {
			    System.out.println("Driver initialization failed. Please check the setup.");
			} else {
			    System.out.println("Driver initialized successfully.");
			}

			
			String timestamp = new SimpleDateFormat("yyyy.MM.ss.HH.mm.ss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot)driver;
			
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + sname+"_"+timestamp;
			File targetFile= new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			return targetFilePath;
			
			
		}
		
	}
