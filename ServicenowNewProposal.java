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

public class ServicenowNewProposal extends BaseClass {
	@Test(dataProvider="proposal")
	public void serviceNowMobile(String value) throws InterruptedException {
		
		WebElement filter = dom.findElementByXPath("//input[@id='filter']");
		filter.sendKeys(value);
		Thread.sleep(2000);
		filter.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement frame1 = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.id("std_change_proposal.short_description")).sendKeys("test");
		//driver.findElement(By.xpath("(//span[text()='Contains unpopulated mandatory fields']/preceding-sibling::span)[2]")).click();
		//span[@class='icon-cross']
		WebElement click = driver.findElement(By.id("sysverb_insert"));
		driver.executeScript("arguments[0].click();", click);
		//driver.findElement(By.id("sysverb_insert_bottom")).click();
		Thread.sleep(3000);
		driver.executeScript("arguments[0].click();", click);
		driver.switchTo().defaultContent();
		WebElement frame2 = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame2);
		WebElement serviceNumber = driver.findElement(By.linkText("STDCHG0001001"));
		String text = serviceNumber.getText();
		System.out.println(text);
		if(text.contains("STD")) {
			System.out.println("Proposal created");
		}
		else
			System.out.println("Proposal not created");
		
	}
	@DataProvider(name="proposal")
	public String[][] fetchData() throws IOException{
		String[][] data;
		data= ServiceNowExcel.readExcelData("Proposal");
		return data;
		
		
		
	}

}
