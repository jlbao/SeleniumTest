package test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

	public static void main(String[] args) {

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(
					new URL("http://localhost:4444/wd/hub"), capability);
			driver.get("https://www.flighthub.com/checkout/deeplink/1e7f8564dbccea6a0cedf04ac71a0cbb/89272686bd75373af0b7050e2acd6b72?country=CA&campaign=120&click_id=B1_s7OzNC4P3RnPeNNM$aw");
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

			WebElement webElement = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath("/html/body/div[2]/div[2]/div[3]/div")));

			WebElement webElement2 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath("/html/body/div[2]/div[2]/div[5]/div/div/div[2]")));

			WebElement priceElem = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[5]/div/div/div[2]/div[2]/div[3]/div/span/strong"));
			
			System.out.println(priceElem.getText());

		} catch (Exception e) {
			driver.close();
		} finally {
			driver.close();
		}
	}
}
