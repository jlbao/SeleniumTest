package test;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

	public static void main(String[] args) {

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		// capability.setVersion("28.0");

		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(
					new URL("http://localhost:4444/wd/hub"), capability);
			driver.get("https://www.flighthub.com/checkout/validate/e2c87278758caa24a147052111fc6e55/eb4c79a1330eeb123bd590acba43b2f9");
			;

			/*
			 * wait till page loading
			 */
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

			/*
			 * explict wait
			 */
			// WebElement webElement = new WebDriverWait(driver, 30)
			// .until(ExpectedConditions.presenceOfElementLocated(By
			// .xpath("/html/body/div[2]/div[2]/div[3]/div")));

			// WebElement priceElem = driver
			// .findElement(By
			// .xpath("/html/body/div[2]/div[2]/div[5]/div/div/div[2]/div[2]/div[3]/div/span/strong"));

			WebElement clickElem = new WebDriverWait(driver, 15)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath("/html/body/div[2]/div[2]/div[2]/a[1]")));
			clickElem.click();
			
			WebElement elem = driver.findElement(By.xpath("//*[@id='modal_box']/div/div[2]/div[1]/ul/li[1]/table[2]/tbody/tr[1]/td[5]"));
			
			System.out.println(elem.getText());
		
			
			/*
			 * Fluent wait until finding the price
			 */
			WebElement priceElem = new FluentWait<WebDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath("/html/body/div[2]/div[2]/div[4]/div/div/div[2]/div[2]/div[3]/div[3]/span/strong")));

			System.out.println(priceElem.getText());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
