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

public class ServicenowNewCaller extends BaseClass {
	@Test(dataProvider = "caller")
	public void serviceNowMobile(String value, String Fname, String Lname, String email, String title, String phone,
			String mobile) throws InterruptedException {
		WebElement filter = dom.findElementByXPath("//input[@id='filter']");
		filter.sendKeys(value);
		Thread.sleep(2000);
		filter.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement frame1 = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.id("sys_user.first_name")).sendKeys(Fname);
		driver.findElement(By.id("sys_user.last_name")).sendKeys(Lname);
		driver.findElement(By.id("sys_user.email")).sendKeys(email);
		driver.findElement(By.id("sys_user.title")).sendKeys(email);
		driver.findElement(By.id("sys_user.phone")).sendKeys(phone);
		driver.findElement(By.id("sys_user.mobile_phone")).sendKeys(mobile);
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		WebElement select = driver.findElement(By.xpath("//select[@role='listbox']"));
		Select dd = new Select(select);
		dd.selectByValue("first_name");
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		search.sendKeys("Nithya");
		Thread.sleep(2000);
		search.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//table[@id='sys_user_table']//tbody//tr[1]//td[4]")).getText();
		System.out.println(text);
		if (text.equals("Nithya")) {
			System.out.println("Caller created");
		} else
			System.out.println("Caller not created");
	}

	@DataProvider(name = "caller")
	public String[][] fetchData() throws IOException {

		String[][] data;

		data = ServiceNowExcel.readExcelData("Caller");

		return data;

	}

}
