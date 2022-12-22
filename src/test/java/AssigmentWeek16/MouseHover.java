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

public class MouseHover {

	WebDriver wd;
	WebDriverWait wDWait;
	SoftAssert sf;
	Actions actions;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.edge.driver", "C:\\Users\\daman\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		wd = new EdgeDriver();
		wd.manage().window().maximize();
		wd.get("http://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");
		wDWait = new WebDriverWait(wd, Duration.ofSeconds(30));
		sf = new SoftAssert();
		actions = new Actions(wd);
	}

	@Test
	public void verifyHoverMouse() {
		WebElement highlightedText = wd.findElement(By.cssSelector("div.columns-inner div h3"));
		sf.assertEquals(highlightedText, "How to perform mouse hover in Selenium Webdriver", "Invalid Title..");
		WebElement automationToolMouseHover = wd.findElement(By.cssSelector("div.dropdown button"));
		actions.moveToElement(automationToolMouseHover).perform();
		// Selenium..
		String firstWindowHandle = wd.getWindowHandle();
		wd.findElement(By.cssSelector("div.dropdown-content a:first-of-type")).click();
		Set<String> windowHandles = wd.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(firstWindowHandle)) {
				wd.switchTo().window(handle);
			}
		}

		sf.assertEquals(wd.getTitle(), "How to perform mouse hover in Selenium Webdriver", "Invalid Title");
		wd.switchTo().window(firstWindowHandle);

		// TestNg..
		wDWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.dropdown button")));
		actions.moveToElement(wd.findElement(By.cssSelector("div.dropdown button"))).perform();
		wDWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.dropdown-content a:nth-of-type(2)")));
		wd.findElement(By.cssSelector("div.dropdown-content a:nth-of-type(2)")).click();
		sf.assertEquals(wd.getTitle(), "TestNG Tutorials for Selenium Webdriver with Real Time Examples",
				"Invalid Title...");
		wd.navigate().back();
	

		// Appium
		wDWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.dropdown button")));
		actions.moveToElement(wd.findElement(By.cssSelector("div.dropdown button"))).perform();
		wDWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.dropdown-content a:nth-of-type(3)")));
		wd.findElement(By.cssSelector("div.dropdown-content a:nth-of-type(3)")).click();
		sf.assertEquals(wd.getTitle(), "Complete Ultimate Appium tutorial for beginners using JAVA for Selenium",
				"Invalid Title...");
		wd.navigate().back();

	}

	@AfterMethod
	public void tearDown() {
		wd.quit();

	}
}
