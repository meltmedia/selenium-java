package com.meltmedia.selenium;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * @author jacobheun
 */
public class WebDriverManagerTest {

  /**
   * Make sure that the webdrivermanager starts out null
   */
  @Test
  public void ensureNullDriver() {

    Assert.assertNull(WebDriverManager.getDriver());

  }

  /**
   * Ensure that the webdriver does not close when there are listeners open
   */
  @Test
  public void ensureListenerCount() {

    // Attach two listeners
    WebDriverManager.requestDriver();
    WebDriverManager.requestDriver();

    // Assert there is a driver
    Assert.assertNotNull(WebDriverManager.getDriver());

    // Remove a listener
    WebDriverManager.releaseDriver();

    // Assert there is still a driver
    Assert.assertNotNull(WebDriverManager.getDriver());

    // Release the second listener
    WebDriverManager.releaseDriver();

    // Assert the driver is null, all listeners should have been collected
    Assert.assertNull(WebDriverManager.getDriver());

  }

  /**
   * Ensure we can create a chrome driver
   */
  @Test
  public void ensureChromeDriver() {

    // Set the system property "webdriver.type" to googlechrome
    System.setProperty("webdriver.type", "googlechrome");

    // Request the driver
    WebDriverManager.requestDriver();

    // Assert the driver is an instance of the chrome driver
    Assert.assertTrue(WebDriverManager.getDriver() instanceof ChromeDriver);

    // Release the driver
    WebDriverManager.releaseDriver();

    // Assert the driver is null
    Assert.assertNull(WebDriverManager.getDriver());

    // Set the system property "webdriver.type" to chrome
    System.setProperty("webdriver.type", "chrome");

    // Request the driver
    WebDriverManager.requestDriver();

    // Assert the driver is an instance of the chrome driver
    Assert.assertTrue(WebDriverManager.getDriver() instanceof ChromeDriver);

    // Release the driver
    WebDriverManager.releaseDriver();

    // Assert the driver is null
    Assert.assertNull(WebDriverManager.getDriver());

  }

  /**
   * Ensure we can create a safari driver
   */
  @Test
  public void ensureSafariDriver() {

    // Set the system property "webdriver.type" to safari
    System.setProperty("webdriver.type", "safari");

    // Request the driver
    WebDriverManager.requestDriver();

    // Assert the driver is an instance of the chrome driver
    Assert.assertTrue(WebDriverManager.getDriver() instanceof SafariDriver);

    // Release the driver
    WebDriverManager.releaseDriver();

    // Assert the driver is null
    Assert.assertNull(WebDriverManager.getDriver());

  }

  /**
   * Ensure the default driver is firefox
   */
  @Test
  public void ensureDefaultDriver() {

    // Set the system property "webdriver.type" to firefox
    System.setProperty("webdriver.type", "firefox");

    // Request the driver
    WebDriverManager.requestDriver();

    // Assert the driver is an instance of the chrome driver
    Assert.assertTrue(WebDriverManager.getDriver() instanceof FirefoxDriver);

    // Release the driver
    WebDriverManager.releaseDriver();

    // Assert the driver is null
    Assert.assertNull(WebDriverManager.getDriver());

    // Set the system property "webdriver.type" to ""
    System.setProperty("webdriver.type", "");

    // Request the driver
    WebDriverManager.requestDriver();

    // Assert the driver is an instance of the chrome driver
    Assert.assertTrue(WebDriverManager.getDriver() instanceof FirefoxDriver);

    // Release the driver
    WebDriverManager.releaseDriver();

    // Assert the driver is null
    Assert.assertNull(WebDriverManager.getDriver());

  }

}
