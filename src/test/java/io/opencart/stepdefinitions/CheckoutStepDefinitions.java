package io.opencart.stepdefinitions;

import io.cucumber.java.en.*;
import io.opencart.pages.CheckoutPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;

public class CheckoutStepDefinitions {

    private WebDriver getDriver() {
        return Hooks.getDriver();
    }

    private CheckoutPage getCheckoutPage() {
        return new CheckoutPage(getDriver());
    }

    @When("the user searches for a product {string}")
    public void userSearchesProduct(String productName) {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("search"))
        );

        searchBox.clear();
        searchBox.sendKeys(productName, Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".product-thumb")
        ));
    }

    @And("the user adds the product to the shopping cart")
    public void userAddsProductToCart() {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // ✅ Step 1: Locate product
        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-thumb"))
        );

        // ✅ Step 2: Scroll to product
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", product);
        sleep(1500);

        // ✅ Step 3: Click cart icon → open product page
        WebElement cartIcon = product.findElement(
                By.xpath(".//div[contains(@class,'button-group')]//button[1]")
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", cartIcon);

        // ✅ Step 4: Wait for product page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'HP')]")
        ));

        // ✅ Step 5: Scroll to Add to Cart button
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("button-cart"))
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
        sleep(1500);

        // ✅ Step 6: Click Add to Cart
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", addToCartBtn);

        // ✅ Step 7: Wait for success alert
        WebElement successAlert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success"))
        );

        sleep(1500);

        // ✅ Step 8: Scroll to TOP
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, 0);");
        sleep(1500);

     // ✅ Wait for success alert (already done)
        WebElement successAlert1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success"))
        );

        // ✅ Click "shopping cart" link INSIDE alert message (100% stable)
        WebElement cartLink = successAlert1.findElement(
                By.xpath(".//a[contains(text(),'shopping cart')]")
        );

        cartLink.click();

    }


    private void sleep(int i) {
		// TODO Auto-generated method stub
		
	}

    @And("the user navigates to the checkout page")
    public void userNavigatesToCheckout() {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ✅ Locate Checkout button
        WebElement checkoutBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[contains(text(),'Checkout')]")
                )
        );

        // ✅ Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

        sleep(1500);

        // ✅ JS click (avoids intercept issue)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", checkoutBtn);
    }
    

    @And("the user provides billing details {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void userProvidesBillingDetails(String fName, String lName, String email,
                                           String phone, String addr, String city, String zip) {
        getCheckoutPage().fillGuestDetails(fName, lName, email, phone, addr, city, zip);
    }

    @And("the user confirms the shipping and payment methods")
    public void userConfirmsShippingAndPayment() {
        getCheckoutPage().acceptShippingAndPayment();
    }

    @And("the user clicks the Final Confirm Order button")
    public void userClicksConfirmOrder() {
        getCheckoutPage().clickConfirmOrder();
    }

    @Then("the order should be placed successfully with confirmation message {string}")
    public void orderPlacedSuccessfully(String expectedMsg) {
        String actualMsg = getCheckoutPage().getConfirmationMessage();
        Assert.assertEquals(actualMsg, expectedMsg,
                "Checkout process failed or confirmation page not loaded!");
    }
}
