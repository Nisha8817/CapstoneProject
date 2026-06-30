package io.opencart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//button[@type='submit' or contains(text(),'Continue')]")
    private WebElement continueButton;

    @FindBy(css = "div.alert.alert-danger")
    private WebElement globalAlertBanner;

    @FindBy(xpath = "//input[@name='firstname']/following-sibling::div[contains(@class,'invalid-feedback')]")
    private WebElement firstNameError;

    @FindBy(xpath = "//input[@name='lastname']/following-sibling::div[contains(@class,'invalid-feedback')]")
    private WebElement lastNameError;

    @FindBy(xpath = "//input[@name='email']/following-sibling::div[contains(@class,'invalid-feedback')]")
    private WebElement emailError;

    @FindBy(xpath = "//input[@name='password']/following-sibling::div[contains(@class,'invalid-feedback')]")
    private WebElement passwordError;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // ✅ Fill form
    public void fillRegistrationForm(String fName, String lName, String email, String password) {

        if (fName != null) {
            firstNameInput.clear();
            if (!fName.isEmpty()) firstNameInput.sendKeys(fName);
        }

        if (lName != null) {
            lastNameInput.clear();
            if (!lName.isEmpty()) lastNameInput.sendKeys(lName);
        }

        if (email != null) {
            emailInput.clear();
            if (!email.isEmpty()) emailInput.sendKeys(email);
        }

        if (password != null) {
            passwordInput.clear();
            if (!password.isEmpty()) passwordInput.sendKeys(password);
        }
    }

    // ✅ Checkbox fix
    public void togglePrivacyPolicy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement checkbox = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='agree']"))
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", checkbox);

        try {
            checkbox.click();
        } catch (Exception e) {
            System.out.println("Normal click failed → using JS click");
            js.executeScript("arguments[0].click();", checkbox);
        }
    }

    // ✅ FINAL STABLE SUBMIT
    public void clickContinue() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // remove overlay if any
        js.executeScript("document.body.classList.remove('modal-open');");

        try {
            WebElement form = driver.findElement(By.id("form-register"));
            js.executeScript("arguments[0].submit();", form);
        } catch (Exception e) {
            System.out.println("Fallback: clicking button");
            continueButton.click();
        }
    }

    // ✅ Field error capture
    public String getFieldError(String fieldName) {
        try {
            switch (fieldName.toLowerCase()) {
                case "firstname":
                    return wait.until(ExpectedConditions.visibilityOf(firstNameError)).getText().trim();
                case "lastname":
                    return wait.until(ExpectedConditions.visibilityOf(lastNameError)).getText().trim();
                case "email":
                    return wait.until(ExpectedConditions.visibilityOf(emailError)).getText().trim();
                case "password":
                    return wait.until(ExpectedConditions.visibilityOf(passwordError)).getText().trim();
                default:
                    return "";
            }
        } catch (Exception e) {
            System.out.println("Timeout waiting for validation: " + fieldName);
            return "";
        }
    }

    // ✅ Alert capture
    public String getAlertText() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(globalAlertBanner))
                       .getText()
                       .trim();
        } catch (Exception e) {
            System.out.println("Alert not found");
            return "";
        }
    }
}
