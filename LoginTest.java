package test2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;

public class LoginTest {
	WebDriver driver;

	@Test (priority = 1)
	public void validUserShouldBeAbleToLogin() {
		driver = BrowserFactory.startBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Call the login method from the LoginPage Class
		loginPage.enterUsername("techfiosdemo@gmail.com");
		loginPage.enterPassword("abc123");
		loginPage.clickSignIn();

		// Validate page show up using the title
		String expectedTitle = "Dashboard- TechFios Test Application - Billing"; // To store the actual title
		String actualTitle = loginPage.getPageTitle(); // To get and store the title
		System.out.println(actualTitle); // To print
		Assert.assertEquals(actualTitle, expectedTitle, "Wrong page!");

		// Validate page show up using the Explicit Wait
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		// Object Reference
		dashboardPage.waitForPage();
	}

	@Test(priority = 2)
	public void invalidUserShouldNotBeAbleToLogin() {

		// Take you to the site
		driver.get("http://techfios.com/test/billing/?ng=admin/");

		// Calling LoginPage Class or Activate
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Call the login method from the LoginPage Class
		loginPage.enterUsername("echfiosdemo@gmail.com");
		loginPage.enterPassword("abc123");
		loginPage.clickSignIn();

		// Validate page show up using the title
		String expectedTitle = "Login - TechFios Test Application - Billing"; // To store the actual title
		String actualTitle = loginPage.getPageTitle(); // To get and store the title
		System.out.println(actualTitle); // To print
		Assert.assertEquals(actualTitle, expectedTitle, "Wrong page!");

		// Validate Dashboard Page did not show up using Expilicit Wait try/catch
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class); // Object Reference
		Assert.assertFalse(dashboardPage.isDashboardDisplayed(), "Invalid User was able to login!!");
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		
	}
	}
