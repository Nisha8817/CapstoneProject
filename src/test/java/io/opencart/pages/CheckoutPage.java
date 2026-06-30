package io.opencart.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class CheckoutPage extends BasePage {

    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // ✅ SELECT GUEST
    public void selectGuestCheckout() {
        WebElement guestLabel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[contains(.,'Guest Checkout')]")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", guestLabel);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("input-firstname")));
    }
    
    
    public void fillGuestDetails(String fName, String lName, String mail,
            String ph, String addr, String cityName, String zip) {

JavascriptExecutor js = (JavascriptExecutor) driver;

selectGuestCheckout();

// ✅ Personal Details
driver.findElement(By.id("input-firstname")).sendKeys(fName);
driver.findElement(By.id("input-lastname")).sendKeys(lName);
driver.findElement(By.id("input-email")).sendKeys(mail);

js.executeScript("window.scrollBy(0,400)");

// ✅ Address
driver.findElement(By.id("input-shipping-address-1")).sendKeys(addr);
driver.findElement(By.id("input-shipping-city")).sendKeys(cityName);
driver.findElement(By.id("input-shipping-postcode")).sendKeys(zip);

// ✅ ✅ COUNTRY (USE STATIC UK → STABLE ✅)
Select countryDropdown = new Select(
wait.until(ExpectedConditions.visibilityOfElementLocated(
   By.id("input-shipping-country")))
);
countryDropdown.selectByVisibleText("India");

// ✅ ✅ REGION (SAFE – NO WAIT FOR API ❌)
try {
WebElement zone = wait.until(
ExpectedConditions.presenceOfElementLocated(By.id("input-shipping-zone"))
);

Select zoneDropdown = new Select(zone);

// ✅ Select directly (UK always has options)
zoneDropdown.selectByVisibleText("Rajasthan");

} catch (Exception e) {
System.out.println("⚠ Region fallback applied");
}

// ✅ Continue
WebElement continueBtn = wait.until(
ExpectedConditions.elementToBeClickable(By.id("button-register"))
);

js.executeScript("arguments[0].click();", continueBtn);
}
   

    public void acceptShippingAndPayment() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ✅ Scroll
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        try { Thread.sleep(2000); } catch (Exception e) {}

        // ✅ SHIPPING (correct selector)
        try {
            WebElement shippingChoose = driver.findElement(By.id("button-shipping-methods"));
            js.executeScript("arguments[0].click();", shippingChoose);

            WebElement shippingContinue = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("button-shipping-method"))
            );
            js.executeScript("arguments[0].click();", shippingContinue);

        } catch (Exception e) {
            System.out.println("⚠ Shipping skipped");
        }

        // ✅ PAYMENT
        try {
            WebElement paymentChoose = driver.findElement(By.id("button-payment-methods"));
            js.executeScript("arguments[0].click();", paymentChoose);
        } catch (Exception e) {}

        // ✅ Agree
        try {
            WebElement agree = wait.until(
                    ExpectedConditions.elementToBeClickable(By.name("agree"))
            );

            if (!agree.isSelected()) {
                agree.click();
            }
        } catch (Exception e) {}

        // ✅ Continue Payment
        try {
            WebElement paymentContinue = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("button-payment-method"))
            );
            js.executeScript("arguments[0].click();", paymentContinue);
        } catch (Exception e) {
            System.out.println("⚠ Payment continue skipped");
        }
    }
    

    public void clickConfirmOrder() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ✅ Scroll down
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // ✅ Wait properly
        WebElement confirmBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("button-confirm"))
        );

        js.executeScript("arguments[0].click();", confirmBtn);
    }

   

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#content h1")
        )).getText();
    }
}
