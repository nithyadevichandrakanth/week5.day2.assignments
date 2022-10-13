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

public class ServicenowKnowledge extends BaseClass {
	@Test(dataProvider = "knowledge")
	public void serviceNowMobile(String value, String KnowledgeBase, String Category, String shortDescription)
			throws InterruptedException {

		WebElement filter = dom.findElementByXPath("//input[@id='filter']");
		filter.sendKeys(value);
		Thread.sleep(2000);
		filter.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement frame1 = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//span[@class='btn-text']")).click();

		WebElement knowledgeBase = driver.findElement(By.id("sys_display.kb_knowledge.kb_knowledge_base"));
		knowledgeBase.sendKeys(KnowledgeBase);
		Thread.sleep(2000);
		knowledgeBase.sendKeys(Keys.DOWN);
		knowledgeBase.sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		WebElement category = driver.findElement(By.id("sys_display.kb_knowledge.kb_category"));
		category.sendKeys(Category);
		Thread.sleep(2000);
		category.sendKeys(Keys.DOWN);
		category.sendKeys(Keys.ENTER);
		Thread.sleep(5000);

		WebElement description = driver.findElement(By.id("kb_knowledge.short_description"));
		description.sendKeys(shortDescription);

		driver.findElement(By.id("sysverb_insert")).click();

	}

	@DataProvider(name = "knowledge")
	public String[][] fetchData() throws IOException {
		String[][] data;
		data = ServiceNowExcel.readExcelData("Knowledge");
		return data;

	}

}
