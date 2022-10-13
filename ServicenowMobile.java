package week5.day2;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ServicenowMobile extends BaseClass{
	@Test(dataProvider="mobile")
	public void serviceNowMobile(String value) throws InterruptedException {
		WebElement filter = dom.findElementByXPath("//input[@id='filter']");
		filter.sendKeys(value);
		Thread.sleep(2000);
		filter.sendKeys(Keys.ENTER);
		// WebElement serviceCatalog =
		// dom.findElementByXPath("//mark[@class='filter-match']");
		// Actions builder = new Actions(driver);
		// builder.doubleClick(serviceCatalog).perform();
		// driver.executeScript("arguments[0].click();", serviceCatalog);
		Thread.sleep(5000);
		WebElement frame1 = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//a[text()='Mobiles']")).click();
		// driver.findElement(By.xpath("//span[contains(text(),'Cell')]")).click();
		driver.findElement(By.xpath("//strong[text()='Apple iPhone 13 pro']")).click();
		driver.findElement(By.xpath("//label[text()='Gold']")).click();
		WebElement dataAllowance = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));
		dataAllowance.click();
		Select dd = new Select(dataAllowance);
		dd.selectByValue("500MB");
		driver.findElement(By.xpath("//label[text()='No']")).click();
		driver.findElement(By.id("oi_order_now_button")).click();
		WebElement requestNumber = driver.findElement(By.id("requesturl"));
		String text = requestNumber.getText();
		System.out.println(text);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		WebElement frame2 = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame2);
		WebElement verifySuccess = driver.findElement(By.xpath("//span[@aria-live='assertive']"));
		String successMessage = verifySuccess.getText();
		if (successMessage.contains("request")) {
			System.out.println("order placed");
		} else
			System.out.println("order not placed");
	}
	@DataProvider(name="mobile")
	public String[][] fetchData() throws IOException {
		
		String[][] data;
		
		data= ServiceNowExcel.readExcelData("Mobile");
		
		return data;
		
	}

}
