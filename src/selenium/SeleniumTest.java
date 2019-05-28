package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver my_dr = new FirefoxDriver();
		my_dr.get("http://www.baidu.com");
		Thread.sleep(1000);
		my_dr.findElement(By.id("kw")).sendKeys("Junit");
		Thread.sleep(1000);
		my_dr.findElement(By.id("su")).click();
		Thread.sleep(1000);
		System.out.println(my_dr.getTitle());
		Thread.sleep(1000);
		my_dr.quit();
		//System.out.println("Hello world");
	}

}
