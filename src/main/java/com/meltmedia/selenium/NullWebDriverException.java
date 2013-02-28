package com.meltmedia.selenium;

/**
 * Informs the user the web driver is null when it should not be
 * @author jacobheun
 */
public class NullWebDriverException extends Exception {

  public NullWebDriverException() {

    this("The current WebDriver must not be null.");

  }

  /**
   * Creates a new NullWebDriverException
   * @param message The exception message
   */
  public NullWebDriverException(String message) {

    super(message);

  }

}
