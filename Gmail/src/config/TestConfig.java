package config;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.pom.Sign_in;

import helper.GetData;

public class TestConfig {
	public static WebDriver driver;

	@BeforeMethod
	public void precondition() {
		String url = GetData.fromExcel("./data/tdata.xlsx", "Browser", 1, 2);
		System.out.println("URL: " + url);

		String browserName = GetData.fromExcel("./data/tdata.xlsx", "Browser", 1, 1);
		System.out.println("Browser: " + browserName);

		if (browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("Chrome")) {
			System.setProperty("WebDriver.chrome.driver", "./browser_executable/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("IE")) {
			System.setProperty("WebDriver.ie.driver", "./browser_executable/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);

		String un = GetData.fromExcel("./data/tdata.xlsx", "Login", 1, 0);
		String pw = GetData.fromExcel("./data/tdata.xlsx", "Login", 1, 1);
		// System.out.println("un: "+un+", "+"pw: "+pw);

		Sign_in s = new Sign_in(driver);
		s.login(un, pw);

	}

	@AfterMethod
	public void postcondition(ITestResult result) {

		// Take Screen Shot
		if (result.getStatus() == 2) {
			EventFiringWebDriver efw = new EventFiringWebDriver(driver);
			File srcFile = efw.getScreenshotAs(OutputType.FILE);
			File destFile = new File("./screenshots/" + System.currentTimeMillis() + ".png");

			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException ioe) {
				Reporter.log("Source or Destination File Error..!!", true);
			}

		}

		driver.close();

	}
}