################ package structure ####################

- src
  - main
    - java
      - com
        - yourcompany
          - pages (package for page objects)
          - testdata (package for test data)
          - tests (package for test classes)
  - test
    - resources
      - testng.xml (TestNG XML configuration file)



   ############## pages package ##############

package com.yourcompany.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void login(String email, String password) {
        driver.findElement(By.id("sTestEmail")).sendKeys(email);
        driver.findElement(By.id("sTestPassword")).sendKeys(password);
        driver.findElement(By.id("sTestLoginBtn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sTest-candidateIcon")));
    }
}




#################### testdata package ########################



package com.yourcompany.testdata;

public class TestData {
    public static final String EMAIL = "abhin714@gmail.com";
    public static final String PASSWORD = "zaq567plm";
    public static final String SEARCH_KEYWORD = "automation qa";
}



################### tests package #######################


package com.yourcompany.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.yourcompany.pages.LoginPage;
import com.yourcompany.testdata.TestData;

public class LoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://recruitcrm.io/");
    }

    @Test
    public void loginAndPerformSearch() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.EMAIL, TestData.PASSWORD);

        Thread.sleep(2000);

        driver.findElement(By.id("sTest-candidateIcon")).click();
        driver.findElement(By.id("sTest-advanceSearchBtn")).click();
        driver.findElement(By.id("sTest-booleanSearchTagInput")).sendKeys(TestData.SEARCH_KEYWORD);

        WebElement dropdownElement = driver.findElement(By.xpath("(//div[@class='field h-auto'])[11]"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue("Yes");

        WebElement dropdownElement1 = driver.findElement(By.xpath("(//div[@class='field h-auto'])[20]"));
        Select dropdown1 = new Select(dropdownElement1);
        dropdown1.selectByValue("Is Available");

        driver.findElement(By.id("sTest-advanceSearchApplyFilters")).click();
    }
}


    ########################### testng.xml  directory ###############






<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="Test Suite">
    <test name="LoginTest">
        <classes>
            <class name="com.yourcompany.tests.LoginTest"/>
        </classes>
    </test>
</suite>

                   
                   
