package week5.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class BaseClass {
	public ChromeDriver driver;
	public Shadow dom;

	@Parameters({ "url", "username", "password"})
	@BeforeMethod
	public void beforMethod(String url, String username, String password) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("sysverb_login")).click();
		dom = new Shadow(driver);
		Thread.sleep(10000);
		dom.setImplicitWait(2);
		WebElement All = dom.findElementByXPath("//div[text()='All']");
		driver.executeScript("arguments[0].click();", All);

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}
