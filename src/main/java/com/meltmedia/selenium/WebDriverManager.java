package com.meltmedia.selenium;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Maintains the state of the current web driver
 *
 * Support drivers for:
 * - firefox
 * - googlechrome
 * - safari
 *
 * Defaults to the firefox driver if the webdriver type is not specified in the build parameters
 * - ex: mvn integration-test -Dwebdriver.type=googlechrome
 *
 * @author jacobheun
 */
public class WebDriverManager {

  private static WebDriver driver;
  private static int numberOfListeners = 0;

  public static final ImmutableMap<String, Class<? extends RemoteWebDriver>> drivers = ImmutableMap.<String, Class<? extends RemoteWebDriver>>builder()
      .put("chrome", ChromeDriver.class)
      .put("googlechrome", ChromeDriver.class)
      .put("safari", SafariDriver.class)
      .put("firefox", FirefoxDriver.class)
      .build();

  private static void createDriver() {
    String driverType = System.getProperty("webdriver.type");

    try {

      if (drivers.containsKey(driverType)) {

        driver = drivers.get(driverType).newInstance();

      } else {

        throw new InstantiationException();

      }

    } catch(InstantiationException ex) {

      driver = new FirefoxDriver();

    } catch (IllegalAccessException ex) {

      driver = new FirefoxDriver();

    }

  }

  /**
   * Gets the current driver. Can be null.
   * @return The current driver
   */
  public static WebDriver getDriver() {

    return driver;

  }

  /**
   * Creates (if needed) and returns an active driver. Must always be accompanied by a releaseDriver call.
   * @return The current driver
   */
  public static WebDriver requestDriver() {

    if (numberOfListeners <= 0) {

      numberOfListeners = 0;
      createDriver();

    }

    numberOfListeners++;

    return driver;

  }

  /**
   * Release the a listener of the driver, will quit the driver if all listeners have been released
   */
  public static void releaseDriver() {

    numberOfListeners--;

    if (numberOfListeners <= 0) {

      numberOfListeners = 0;
      driver.quit();
      driver = null;

    }

  }

}
