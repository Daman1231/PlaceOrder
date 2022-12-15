package com.Daman.SeleniumAssignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrder {
	WebDriver wd;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\daman\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		wd = new EdgeDriver();
		wd.manage().window().maximize();

		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		sleep();
		wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void verifyOrderPlaced() {
		WebElement emailInputField = wd.findElement(By.cssSelector("form div.form-group:first-of-type input"));
		WebElement passwordInputField = wd.findElement(By.cssSelector("form div.form-group:last-of-type input"));
		emailInputField.sendKeys("daman.singh007@gmail.com");
		sleep();
		passwordInputField.sendKeys("Daman1234");
		sleep();

		wd.findElement(By.cssSelector("input[value='Login']")).click();
		String title = wd.getTitle();
		Assert.assertEquals(title, "My Account", "Invalid Credentials");
		sleep();

		//// #menu>div:last-of-type>ul>li:nth-of-type(2)>a
		wd.findElement(By.cssSelector("#menu>div:last-of-type>ul>li:nth-of-type(2)>a")).click();
		sleep();
		wd.findElement(By.cssSelector("#menu> div:last-of-type>ul>li:nth-of-type(2)>div>a")).click();
		wd.findElement(By.cssSelector("#content>div:nth-of-type(4)>div:nth-of-type(3)>div>div:last-of-type a")).click();
//		wd.findElement(By.cssSelector("div.product-layout:first-of-type div.product-thumb>div:nth-of-type(2) a"))
//				.click();
		sleep();
		wd.findElement(By.cssSelector("button[id='button-cart']")).click();
		sleep();
		String readText = wd.findElement(By.cssSelector("#cart-total")).getText();
		Assert.assertEquals(readText, "1 item(s) - $1,000.00", "There is More than 1 item in cart");
		wd.findElement(By.cssSelector("div.col-sm-3 div button[data-loading-text='Loading...']")).click();
		sleep();
		wd.findElement(By.cssSelector("p[class='text-right'] a:nth-of-type(2)")).click();
		sleep();
		wd.findElement(By.cssSelector("form.form-horizontal>div:nth-of-type(3 ) input")).click();

		WebElement firstNameInput = wd.findElement(By.cssSelector("div.col-sm-10 input[name='firstname']"));
		WebElement lastNameInput = wd.findElement(By.cssSelector("div.col-sm-10 input[name='lastname']"));
		WebElement addressInputField = wd.findElement(By.cssSelector("div.col-sm-10 input[name='address_1']"));
		WebElement cityInputField = wd.findElement(By.cssSelector("div.col-sm-10 input[name='city']"));
		WebElement postelCodeInputField = wd.findElement(By.cssSelector("div.col-sm-10 input[name='postcode']"));

		firstNameInput.sendKeys("DamanPreetS");
		lastNameInput.sendKeys("Singh");
		addressInputField.sendKeys("66 Humber College Blvd");
		cityInputField.sendKeys("Etobicoke");
		postelCodeInputField.sendKeys("M9V 1P5");

		wd.findElement(By.cssSelector("select[name='country_id']")).click();
		selectByVisibleText(wd.findElement(By.cssSelector("select[name='country_id']")), "Canada");
		sleep();

		wd.findElement(By.cssSelector("select[name='zone_id']")).click();
		selectByVisibleText(wd.findElement(By.cssSelector("select[name='zone_id']")), "Ontario");
		sleep();

		wd.findElement(By.cssSelector("input[value='Continue']")).click();
		sleep();
		wd.findElement(By.cssSelector("input[id='button-shipping-address']")).click();

		WebElement radioBtn = wd.findElement(By.cssSelector("input[value='flat.flat']"));
		WebElement inputCommentField = wd.findElement(By.cssSelector("textarea[name='comment']"));
		inputCommentField.sendKeys("Please Pack a Mobile Phone in a Gift Wrap... Thanks");
		wd.findElement(By.cssSelector("input[id='button-shipping-method']")).click();
		sleep();
		wd.findElement(By.cssSelector("input[type='checkbox']")).click();
		wd.findElement(By.cssSelector("#button-payment-method:last-of-type")).click();
		WebElement productName = wd.findElement(By.cssSelector("div.table-responsive a"));
		System.out.println(productName.getText());
		String name = productName.getText();
		Assert.assertEquals(name, "MacBook Air", "Failed to get Desire item");
		WebElement quantity = wd.findElement(By.cssSelector("div.table-responsive>table tbody td:nth-of-type(3)"));
		Assert.assertEquals(quantity.getText(), "1", "there is more than 1 item");
		wd.findElement(By.cssSelector("#button-confirm:last-of-type")).click();
		sleep();
		WebElement finalText = wd.findElement(By.cssSelector("#content h1"));
		Assert.assertEquals(finalText.getText(), "Your order has been placed!", "Order Not Placed???");
	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}

	public void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectByVisibleText(WebElement element, String text) {
		Select sc = new Select(element);
		sc.selectByVisibleText(text);
	}

}
