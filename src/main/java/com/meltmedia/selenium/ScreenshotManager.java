package com.meltmedia.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;

/**
 * Maintains methods for selenium based screenshots. Requires the usage of the WebDriverManager for running drivers.
 * @author jacobheun
 */
public class ScreenshotManager {

  private String root = "target/screenshots/";
  private String failurePath = "failure/";
  private String extension = ".png";

  /**
   * Attempts to save a screenshot with the given filename to the current driver
   * Saves the filename under the root path of target/screenshots
   * @param screenshotFileName The name of the file to be saved
   * @throws IOException Throws an ioexception if the file cannot be saved
   */
  public void saveScreenshot(String screenshotFileName) throws IOException, NullWebDriverException, WebDriverException, IllegalArgumentException {

    if (screenshotFileName == null) {

      throw new IllegalArgumentException("Screenshot file name must not be null.");

    }

    screenshotFileName = screenshotFileName.concat(extension);

    String fileName = root.concat(screenshotFileName);
    WebDriver driver = WebDriverManager.getDriver();

    if (driver == null) {

      throw new NullWebDriverException();

    }

    // Take the screenshot
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshot, new File(fileName));

  }

  /**
   * Will prepend the filename with the failure directory
   * @param screenshotFileName
   * @throws IOException
   */
  public void saveFailureScreenshot(String screenshotFileName) throws IOException, NullWebDriverException, WebDriverException, IllegalArgumentException {

    if (screenshotFileName == null) {

      throw new IllegalArgumentException("Screenshot file name must not be null.");

    }

    // Change our directory
    String fileName = failurePath.concat(screenshotFileName);

    // Snap the screenshot
    saveScreenshot(fileName);

  }

}
