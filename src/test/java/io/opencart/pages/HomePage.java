package io.opencart.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BasePage {

	@FindBy(css = "a[title='My Account']")
	private WebElement myAccountDropdown;

	@FindBy(xpath = "//a[text()='Register']")
	private WebElement registerLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToRegister() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        WebElement account = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.cssSelector("a[title='My Account']")));

        // JS click to avoid overlay issues
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", account);

        WebElement register = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//a[text()='Register']")));

        js.executeScript("arguments[0].click();", register);
    }
}
