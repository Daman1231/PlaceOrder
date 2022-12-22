package AssigmentWeek16;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidatePurchaseOfItem {

	WebDriver wd;
	WebDriverWait wDWait;
	SoftAssert sf;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.edge.driver", "C:\\Users\\daman\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		wd = new EdgeDriver();
		wd.manage().window().maximize();
		wd.get("https://www.demoblaze.com/index.html");
		wDWait = new WebDriverWait(wd, Duration.ofSeconds(30));
		sf = new SoftAssert();
	}

	@Test
	public void validatePurchaseOfItem() throws InterruptedException {
		wDWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div.col-lg-9>div>div:last-of-type>div>div a")));
		WebElement sonyVaio = wd.findElement(By.cssSelector("div.col-lg-9>div>div:last-of-type>div>div a"));
		sonyVaio.click();
		wDWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.row h2")));
		// Validating..
		WebElement sonyVaioText = wd.findElement(By.cssSelector("div.row h2"));
		sf.assertEquals(sonyVaioText.getText(), "Sony vaio i7", "Invalid Device");
		WebElement sonyVaioPrice = wd.findElement(By.cssSelector("div.row h3"));
		wDWait.until(ExpectedConditions.visibilityOf(sonyVaioText));
		sf.assertEquals(sonyVaioPrice.getText(), "$790 *includes tax", "Invalid Price");
		sf.assertAll();
		WebElement addToCart = wd.findElement(By.cssSelector("div.row:nth-of-type(2) div a"));
		addToCart.click();
		wDWait.until(ExpectedConditions.alertIsPresent());
		wd.switchTo().alert().accept();
		WebElement cart = wd.findElement(By.cssSelector("li[class='nav-item']:nth-of-type(4) a"));
		cart.click();
		wDWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr.success td:nth-of-type(2)")));
		WebElement validateTitle = wd.findElement(By.cssSelector("tr.success td:nth-of-type(2)"));
		sf.assertEquals(validateTitle, "Sony vaio i7", "Invalid phone Title...");
		WebElement validatePrice = wd.findElement(By.cssSelector("tr.success td:nth-of-type(3)"));
		sf.assertEquals(validatePrice, "790", "Invalid Phone Price...");
		WebElement validateTotalPrice = wd.findElement(By.cssSelector("div.panel-heading h3"));
		sf.assertEquals(validateTotalPrice, "790", "Invalid Total Price");
		wDWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-target='#orderModal']")));
		wd.findElement(By.cssSelector("button[class='btn btn-success']")).click();

		wDWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#name")));
		WebElement nameImputField = wd.findElement(By.cssSelector("#name"));
		WebElement countryInputField = wd.findElement(By.cssSelector("#country"));
		WebElement cityInputField = wd.findElement(By.cssSelector("#city"));
		WebElement creditCardInputField = wd.findElement(By.cssSelector("#card"));
		WebElement monthInputField = wd.findElement(By.cssSelector("#month"));
		WebElement yearInputField = wd.findElement(By.cssSelector("#year"));

		nameImputField.sendKeys("Daman Preet Singh");
		countryInputField.sendKeys("Canada");
		cityInputField.sendKeys("Toronto");
		creditCardInputField.sendKeys("3554699842");
		monthInputField.sendKeys("July");
		yearInputField.sendKeys("1993");
		wd.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
		wDWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[class='sweet-alert  showSweetAlert visible']>h2")));
		WebElement validateThankyouMessage = wd
				.findElement(By.cssSelector("div[class='sweet-alert  showSweetAlert visible']>h2"));
		sf.assertEquals(validateThankyouMessage, "Thank you for your purchase!", "Purchase Unsuccesfully..");
		boolean details = validateThankyouMessage.getText()
				.contains("Amount: 790 USD" + "Card Number: 3554699842" + "Name: Daman Preet Singh");
		sf.assertTrue(details, "Highlighted and entered details validate Succesfull...");
		wDWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div.sa-confirm-button-container button")));
		wd.findElement(By.cssSelector("div.sa-confirm-button-container button")).click();
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();

	}

}
