# Demo Web Shop Automation Framework

## Overview

This project is a Selenium WebDriver-based Test Automation Framework developed for automating the Demo Web Shop application using:
* Java
* Selenium WebDriver
* Cucumber BDD
* TestNG
* Maven
* Page Object Model (POM)
* Allure Reports
* Extent Reports
* Apache POI (Excel Data Handling)

The framework follows industry-standard automation practices with reusable page objects, feature-driven development, reporting, and data-driven testing.





## Application Under Test

*Website:* https://tricentis.com
The framework automates key e-commerce functionalities including:
* User Registration
* User Login
* Product Search
* Product Filtering
* Product Comparison
* Wishlist Management
* Shopping Cart Operations
* Guest Checkout
* Registered User Checkout
* Order Placement
* Recently Viewed Products
* Account Management
* Return Requests

├── App.java
├── DatabaseConnection.java
├── CompareProductsPage.java
├── ContactUsPage.java
├── ExcelLoginUtility.java
├── GuestCheckoutPage.java
├── HomePage.java
├── LoginPage.java
├── ProductDetailsPage.java
├── ProductFilterPage.java
├── ProductSortingPage.java
├── RecentlyViewedPage.java
├── RegisteredCheckoutPage.java
├── RegisterPage.java
├── ReturnRequestPage.java
├── SearchResultsPage.java
├── WishlistPage.java

## Feature Coverage

### User Management
* User Registration
* User Login
* User Logout
* Account Information Management

### Product Operations
* Product Search
* Category Navigation
* Product Sorting
* Product Filtering
* Product Details Validation

### Shopping Features
* Add Product to Cart
* Update Cart
* Remove Product from Cart
* Compare Products
* Add Products to Wishlist

### Checkout Features
* Guest Checkout
* Registered User Checkout
* Order Confirmation

### Customer Services
* Contact Us
* Return Request
* Recently Viewed Products

---

## Test Data Management
Excel-based test data handling is implemented using:
* 'UserCredentials.xlsx'
* 'ExcelLoginUtility.java'

### Benefits:
* Data-driven testing
* Reusable test data
* Easy maintenance
* Reduced hardcoding

## Cucumber BDD Implementation
Feature files are stored under:

src/test/resources/features

### Example:

Cucumber
Feature: Product Search

  Scenario: Search product and add to cart
    Given the user is on the Demo Web Shop homepage
    When the user searches for a product
    Then matching products should be displayed


## Test Execution

### Run All Scenarios

Shell
mvn clean test


### Run Specific Scenario Using Tags

Example:

Java
@CucumberOptions(
    tags = "@ProductSearch"
)

*Execute:*

Shell
mvn test


### Run Through TestNG

Plain Text
* Right Click -> Run As -> TestNG Suite


## Reporting

### Allure Report

#### Generate Results:

Shell
mvn test


#### Generate Report:

html
<!-- test-output/ -->

### Provides:
* Pass/Fail Status
* Execution Summary
* Screenshots
* Test Logs

## Design Patterns Used

### Page Object Model (POM)
#### Advantages:
* Reusable code
* Easy maintenance
* Better readability
* Reduced code duplication

### Data-Driven Testing
#### Advantages:
* Multiple test combinations
* Test-data separation
* Better scalability

### Behavior Driven Development (BDD)
#### Advantages:
* Collaboration
* Business-readable scenarios
* Better documentation

## Maven Dependencies
### Main dependencies used:

XML
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
</dependency>

<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
</dependency>

<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-testng</artifactId>
</dependency>

<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
</dependency>

<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-cucumber7-jvm</artifactId>
</dependency>

## Key Framework Features
* Selenium WebDriver Automation
* Cucumber BDD Framework
* TestNG Integration
* Page Object Model
* Data-Driven Testing
* Excel Utility Support
* Allure Reporting
* Extent Reporting
* Reusable Components
* Scalable Framework Structure
* Maven Build Management

## Authors
* Nisha Mandwal
* Pammi Yashwitha Reddy
* Pasagadugula Sree Keerthana
* Mamatha Potunuru
* Arthi Prasanna Reddy Musatla
* Kandukuri Siddarth Goud

 
*Role:* Project Engineer | SDET Automation Engineer 

*Project Type:*
End-to-End E-Commerce Automation Testing Framework for Demo Web Shop Application using Selenium, Cucumber, TestNG, Maven, and Page Object Model.

