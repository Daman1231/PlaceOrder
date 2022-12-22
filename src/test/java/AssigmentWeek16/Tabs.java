package AssigmentWeek16;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Tabs {

	WebDriver wd;
	WebDriverWait wDWait;
	SoftAssert sf;
	Actions actions;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.edge.driver", "C:\\Users\\daman\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		wd = new EdgeDriver();
		wd.manage().window().maximize();
		wd.get("http://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html");
		wDWait = new WebDriverWait(wd, Duration.ofSeconds(30));
		sf = new SoftAssert();
		actions = new Actions(wd);
	}

	@Test
	public void verifyTabsTitle() {
		WebElement title = wd.findElement(By.cssSelector("div.post-outer div h3"));
		sf.assertEquals(title.getText(), "Multiple window examples", "Invalid Title...");
		// link 1..
		String firstWindowHandle = wd.getWindowHandle();
		wd.findElement(By.cssSelector("#post-body-6170641642826198246 a:first-of-type")).click();
		Set<String> allWindowHandles = wd.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!handle.equals(firstWindowHandle)) {
				wd.switchTo().window(handle);
			}
		}
		sf.assertEquals(wd.getTitle(), "Google");
		wd.switchTo().window(firstWindowHandle);

		// link 2

		wDWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(2)")));
		wd.findElement(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(2)")).click();

		Set<String> allWindowHandle = wd.getWindowHandles();
		String secondWindowHandle = wd.getWindowHandle();

		for (String handle : allWindowHandle) {
			if (!handle.equals(secondWindowHandle)) {
				wd.switchTo().window(handle);
			}
		}
		sf.assertEquals(wd.getTitle(), "Facebook - log in or sign up", "Invalid Title");
		wd.switchTo().window(firstWindowHandle);

		// link 3

		wDWait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(3)")));
		wd.findElement(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(3)")).click();
		String thirdWindowHandle = wd.getWindowHandle();
		Set<String> allWindowHandless = wd.getWindowHandles();
		for (String handle : allWindowHandless) {
			if (!handle.equals(thirdWindowHandle)) {
				wd.switchTo().window(handle);
			}
		}

		sf.assertEquals(wd.getTitle(),
				"Yahoo | Mail, Weather, Search, News, Finance, Sports, Shopping, Entertainment, Video",
				"Invalid Title");
		wd.switchTo().window(firstWindowHandle);
		sf.assertAll();

		wDWait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(4)")));
		wd.findElement(By.cssSelector("#post-body-6170641642826198246 a:nth-of-type(4)")).click();
		String fourthWindowHandle = wd.getWindowHandle();
		Set<String> allhandles = wd.getWindowHandles();
		for (String handle : allhandles) {
			if (!handle.equals(fourthWindowHandle)) {
				wd.switchTo().window(handle);
			}
		}
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();

	}
}
