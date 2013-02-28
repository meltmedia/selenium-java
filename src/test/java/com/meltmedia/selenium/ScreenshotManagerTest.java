package com.meltmedia.selenium;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;

/**
 * @author jacobheun
 */
public class ScreenshotManagerTest {


  /**
   * Ensures that attempting to save a failure screenshot with a bad file path results in an IOException
   * @throws IOException
   * @throws NullWebDriverException
   * @throws WebDriverException
   */
  @Test(expected = IllegalArgumentException.class)
  public void badFailureFilePath() throws IOException, NullWebDriverException, WebDriverException {

    String badFileName = null;

    WebDriverManager.requestDriver();

    ScreenshotManager screenshotManager = new ScreenshotManager();

    try {

      screenshotManager.saveFailureScreenshot(badFileName);

    } finally {

      WebDriverManager.releaseDriver();

    }

    Assert.assertNull(WebDriverManager.getDriver());

  }

  /**
   * Ensures that attempting to save a screenshot with a bad file path results in an IOException
   * @throws IOException
   * @throws NullWebDriverException
   * @throws WebDriverException
   */
  @Test(expected = IllegalArgumentException.class)
  public void badFilePath() throws IOException, NullWebDriverException, WebDriverException {

    String badFileName = null;

    WebDriverManager.requestDriver();

    ScreenshotManager screenshotManager = new ScreenshotManager();

    try {

      screenshotManager.saveScreenshot(badFileName);

    } finally {

      WebDriverManager.releaseDriver();

    }

    Assert.assertNull(WebDriverManager.getDriver());

  }

  /**
   * Ensures that attempting to save a failure screenshot with a good file path succeeds
   * @throws IOException
   * @throws WebDriverException
   * @throws NullWebDriverException
   */
  @Test
  public void goodFailureFilePath() throws IOException, WebDriverException, NullWebDriverException {

    String goodFileName = "my_cool_system_test";

    WebDriverManager.requestDriver();

    ScreenshotManager screenshotManager = new ScreenshotManager();

    try {

      screenshotManager.saveFailureScreenshot(goodFileName);

    } finally {

      WebDriverManager.releaseDriver();

    }

    Assert.assertNull(WebDriverManager.getDriver());

  }

  /**
   * Ensures that attempting to save a screenshot with a good file path succeeds
   * @throws IOException
   * @throws WebDriverException
   * @throws NullWebDriverException
   */
  @Test
  public void goodFilePath() throws IOException, WebDriverException, NullWebDriverException {

    String goodFileName = "myTest02/an-action";

    WebDriverManager.requestDriver();

    ScreenshotManager screenshotManager = new ScreenshotManager();

    try {

      screenshotManager.saveScreenshot(goodFileName);

    } finally {

      WebDriverManager.releaseDriver();

    }

    Assert.assertNull(WebDriverManager.getDriver());


  }

  /**
   * Ensure that when the WebDriver is null a NullWebDriverException is thrown
   * @throws IOException
   * @throws WebDriverException
   * @throws NullWebDriverException
   */
  @Test(expected = NullWebDriverException.class)
  public void nullWebDriverFailureScreenshot() throws IOException, WebDriverException, NullWebDriverException {

    String goodFileName = "myTest02/an-action";

    Assert.assertNull(WebDriverManager.getDriver());

    ScreenshotManager screenshotManager = new ScreenshotManager();

    screenshotManager.saveFailureScreenshot(goodFileName);

  }

  /**
   * Ensure that when the WebDriver is null a NullWebDriverException is thrown
   * @throws IOException
   * @throws WebDriverException
   * @throws NullWebDriverException
   */
  @Test(expected = NullWebDriverException.class)
  public void nullWebDriverScreenshot() throws IOException, WebDriverException, NullWebDriverException {

    String goodFileName = "myTest02/an-action";

    Assert.assertNull(WebDriverManager.getDriver());

    ScreenshotManager screenshotManager = new ScreenshotManager();

    screenshotManager.saveScreenshot(goodFileName);

  }


}