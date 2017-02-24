package com.leantrips.tests;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUp {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.leantrips.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSignUp() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Sign Up")).click();
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys("Lean");
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("Trips2");
    driver.findElement(By.id("emailAddress")).clear();
    driver.findElement(By.id("emailAddress")).sendKeys("leantrips2@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("leantrips2");
    driver.findElement(By.id("confirmpassword")).clear();
    driver.findElement(By.id("confirmpassword")).sendKeys("leantrips2");
    new Select(driver.findElement(By.id("birthDay"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("birthMonth"))).selectByVisibleText("Jan");
    new Select(driver.findElement(By.id("birthYear"))).selectByVisibleText("1999");
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    driver.findElement(By.xpath("//header-model/div[2]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
